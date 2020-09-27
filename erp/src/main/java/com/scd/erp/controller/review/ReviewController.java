package com.scd.erp.controller.review;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scd.erp.mapper.FileMapper;
import com.scd.erp.service.review.ContentService;
import com.scd.erp.service.review.FileUploadService;
import com.scd.erp.service.review.ReviewService;
import com.scd.erp.user.review.Content;
import com.scd.erp.user.review.ContentFile;
import com.scd.erp.user.review.Record;
import com.scd.erp.utils.common.FileUtils;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;


@RequestMapping("review/")
@RestController
public class ReviewController {

    private final ReviewService rs;
    private final FileMapper fileMapper;
    private final ContentService cs;
    private final FileUploadService fus;

    @Autowired private FileUtils fu;
    private LoginUtile lu;

    @Autowired
    public ReviewController(ReviewService rs, FileMapper fileMapper, ContentService cs, FileUploadService fus) {

        this.lu = new LoginUtile();
        this.rs = rs;
        this.fileMapper = fileMapper;
        this.cs = cs;
        this.fus = fus;
    }

    /**
     * 评审添加
     *
     * @param record
     * @param contents
     * @param ids
     * @param personIDs
     * @return
     */
    @RequestMapping(value = "/addReviewMsg.do", method = RequestMethod.POST)
    @ResponseBody
    public Map addReviewMsg(Record record, Content contents, String ids, String personIDs,String rfids) {
        return rs.addReviewMsg(contents, ids, record, personIDs,rfids);

    }

    /**
     * 评审追加
     *
     * @param contents
     * @return
     */
    @RequestMapping(value = "/addContent.do", method = RequestMethod.POST)
    @ResponseBody
    public Content addContentMsg(Content contents) {
        return rs.addContentMsg(contents);

    }

    /**
     * @param obj
     */

    //测试接收数组数据接口{"ids",[1,2,3]}
    @RequestMapping(value = "/test.do", method = RequestMethod.POST)
    @ResponseBody
    public void test(@RequestBody JSONObject obj) {
        String tt = obj.getString("ids");
        //System.out.println("交换机的时间="+tt);

        JSONArray createArray = JSONArray.parseArray(tt);
        for (int i = 0; i < createArray.size(); i++) {
            Integer t = Integer.valueOf(JSONObject.toJSONString(createArray.get(i)));
            System.out.println(t);
        }

    }

    //测试接收数组数据接口{"ids",[1,2,3]}
    @RequestMapping(value = "/test1.do", method = RequestMethod.POST)
    @ResponseBody
    public void test1(String ids) {


        String[] id = ids.split(",");
        for (int i = 0; i < id.length; i++) {
            //  System.out.println("交换机的时间="+id[i]);
            int rid = Integer.valueOf(id[i]);
        }

    }


    @RequestMapping("/upload.do")
    @ResponseBody
    public List upload(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
        try {
            List list = new ArrayList();
            for (MultipartFile item : uploadFile) {
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                if (!path.exists()) {
                    path = new File("");
                }
                File upFile = new File(path.getAbsolutePath(), "static/RecordFile/");
                if (!upFile.exists()) {
                    upFile.mkdirs();
                }

                String fname = item.getOriginalFilename();
                String suffix = fname.split("\\.")[1];
                String filename = UUID.randomUUID().toString() + "." + suffix;
                File dir = new File(path.getAbsolutePath(), "/static/RecordFile/" + filename);
                System.out.println(dir.getPath());
                item.transferTo(dir);

                String url = "/RecordFile/" + filename;
                ContentFile contentFile = new ContentFile();
                contentFile.setFilename(fname);
                contentFile.setUrl(url);
                fus.saveFileMsg(contentFile);
                list.add(contentFile.getRfid());

            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/selectRecordMg.do", method = RequestMethod.POST)
    @ResponseBody
    public List<Record> selectRecordMg(Integer pid, Integer rid, HttpSession session) {
        Integer personID = lu.getUser(session).getUserid();
        return rs.selectRecordMg(rid, personID, pid);

    }

    @RequestMapping(value = "/deleteRecordMg.do", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteRecordMg(Integer rid, String rfids) {
        return rs.deleteRecordMg(rid, rfids);

    }

    @RequiresPermissions("down:doc")
    @RequestMapping("download.do")
    public void download(HttpServletResponse response,
                         @RequestParam("ids") String ids) throws Exception {
        //System.out.println(ids);
        if (null != ids && !ids.equals("") && !ids.equals("null")) {
            String[] IDs = ids.split("_");
            for (String ID : IDs) {
                ContentFile docement = this.fileMapper.selectByPrimaryKey(Integer.parseInt(ID));
                String name = docement.getFilename();
                fu.download(name != null ? name : "未知文件", "static" + docement.getUrl(), response);
            }
        }
    }


}
