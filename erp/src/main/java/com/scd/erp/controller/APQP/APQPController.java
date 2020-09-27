package com.scd.erp.controller.APQP;

import com.scd.erp.mapper.APQPFileMapper;
import com.scd.erp.service.APQP.APQPFileService;
import com.scd.erp.service.APQP.APQPService;
import com.scd.erp.user.APQP.APQPFile;
import com.scd.erp.user.APQP.APQPFmea;
import com.scd.erp.utils.common.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("apqp/")
@RestController
public class APQPController {
    private final APQPService apqpService;
    private final APQPFileService apqpFileService;
    private final APQPFileMapper apqpFileMapper;
    @Autowired private FileUtils fu;

    @Autowired
    public APQPController(APQPService apqpService, APQPFileService apqpFileService, APQPFileMapper apqpFileMapper) {
        this.apqpFileMapper = apqpFileMapper;
        this.apqpService = apqpService;
        this.apqpFileService = apqpFileService;
    }

    @RequestMapping(value = "/addAPQPMsg.do", method = RequestMethod.POST)
    @ResponseBody
    public int addAPQPMsg(APQPFmea apqpFmea,String ids,String picname) throws IOException {
        return apqpService.insertApqpMsg(apqpFmea,ids,picname);
    }

    @RequestMapping(value = "/updateAPQPMsg.do", method = RequestMethod.POST)
    @ResponseBody
    public int updateAPQPMsg(APQPFmea apqpFmea,Integer fid,String picname, HttpServletResponse response) throws IOException {
        return apqpService.updateAPQPMsg(apqpFmea,fid,picname,response);
    }

    @RequestMapping(value = "/selectAPQPMsg.do", method = RequestMethod.POST)
    @ResponseBody
    public List<APQPFmea> selectAPQPMsg(Integer id) {
        return apqpService.selectAPQPMsg(id);
    }

    @RequestMapping(value = "/selectAPQPFileMsg.do", method = RequestMethod.POST)
    @ResponseBody
    public List<APQPFile> selectAPQPFileMsg(Integer id) {
        return apqpService.selectAPQPFileMsg(id);
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
                File upFile = getFile(path, "static/APQP/");
                if (!upFile.exists()) {
                    upFile.mkdirs();
                }

                String fname = item.getOriginalFilename();
                String suffix = fname.split("\\.")[1];
                String filename = UUID.randomUUID().toString() + "." + suffix;
                File dir = getFile(path, "/static/APQP/" + filename);
                System.out.println(dir.getPath());
                item.transferTo(dir);

                String url = "/APQP/" + filename;
                APQPFile apqpFile = new APQPFile();
                apqpFile.setApqp_file_name(fname);
                apqpFile.setApqp_file_url(url);
                apqpFileService.saveFileMsg(apqpFile);
                list.add(apqpFile.getFid());
                list.add(apqpFile.getApqp_file_url());

            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("download.do")
    public void download(HttpServletResponse response,
                         @RequestParam("ids") String ids) throws Exception {
        //System.out.println(ids);
        if (null != ids && !ids.equals("") && !ids.equals("null")) {
            String[] IDs = ids.split(",");
            for (String ID : IDs) {
                APQPFile docement = getContentFile(ID);
                String name = docement.getApqp_file_name();
                fu.download(name != null ? name : "未知文件", "static/" + docement.getApqp_file_url(), response);
            }
        }
    }

    private APQPFile getContentFile(String ID) {
        return this.apqpFileMapper.selectByID(Integer.parseInt(ID));
    }

    private File getFile(File path, String s) {
        return new File(path.getAbsolutePath(), s);
    }

}
