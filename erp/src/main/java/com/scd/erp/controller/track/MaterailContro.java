package com.scd.erp.controller.track;

import com.scd.erp.Vo.Materail.Materail;
import com.scd.erp.service.trace.MaterailService;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "materail", method = RequestMethod.POST)
public class MaterailContro {

    private final MaterailService materailService;
    private LoginUtile lu;

    @Autowired
    public MaterailContro(MaterailService materailService) {
        this.lu = new LoginUtile();
        this.materailService = materailService;
    }


    @RequiresPermissions("select:mater")
    @RequestMapping("getOne.do")
    public Materail getOne(Materail materail) {
        return this.materailService.getOne(materail);
    }

    @RequiresPermissions("add:mater")
    @RequestMapping("Set.do")
    public List<Materail> setMater(Materail materail) {
        this.materailService.setMater(materail);
        return getList();
    }

    @RequiresPermissions("select:maters")
    @RequestMapping("List.do")
    public List<Materail> getList() {
        return this.materailService.getList();
    }


    @RequiresPermissions("updata:maters")
    @PostMapping("upload.do")
    public Integer upload(@RequestParam("uploadFile") MultipartFile uploadFile,
                          HttpServletRequest request,
                          @RequestParam("type") Integer type) {
        //System.out.println(uploadFile == null);
        Integer userid = lu.getUser(request).getUserid();
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            File upFile = new File(path.getAbsolutePath(), "static/uploadFile/");
            if (!upFile.exists()) {
                upFile.mkdirs();
            }
            String fname = uploadFile.getOriginalFilename();
            String suffix = fname.split("\\.")[1];
            String filename = UUID.randomUUID().toString() + "." + suffix;
            File dir = new File(path.getAbsolutePath(), "/static/uploadFile/" + filename);
            //System.out.println(dir.getPath());
            uploadFile.transferTo(dir);


            String filePath = "/uploadFile/" + filename;
            Materail materail = new Materail();
            materail.setCreatid(userid);
            materail.setMaterialurl(filePath);
            materail.setMaterialtype(type);
            materail.setMaterialname(fname);
            this.materailService.setMater(materail);
            //3，返回可供访问的网络路径
            Integer materialid = materail.getMaterialid();
            //System.out.println(materialid+">>>>>");
            return materialid;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @RequiresPermissions("updata:maters")
    @RequestMapping("uploads")
    public List<Integer> uploads(HttpServletRequest request,
                                 @RequestParam("type") Integer type) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("uploadFile");

        if (files.size() > 0) {
            List<Integer> ids = new LinkedList<>();
            for (MultipartFile file : files) {
                ids.add(this.upload(file, request, type));
            }
            return ids;
        } else {
            return null;
        }
    }
    @RequiresPermissions("del:maters")
    @RequestMapping("Del.do")
    public boolean delete(Materail materail) {
        return this.materailService.del(materail);
    }

    @RequiresPermissions("down:mates")
    @RequestMapping("download.do")
    public void download(HttpServletResponse response, @RequestParam("ids") String ids) throws Exception {
        String[] IDs = ids.split("_");
        for (String ID : IDs) {
            Materail materail = new Materail();
            materail.setMaterialid(Integer.parseInt(ID));
            materail = this.getOne(materail);
            String filePath = new ApplicationHome(getClass()).getDir().getParent() + "/classes/static"
                    + materail.getMaterialurl();
            // 文件地址，真实环境是存放在数据库中的
            File file = new File(filePath);
            // 穿件输入对象
            FileInputStream fis = new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");

            // 设置下载后的文件名以及header
            String name = materail.getMaterialname();
            response.addHeader("Content-disposition", "attachment;fileName=" +
                    URLEncoder.encode(name != null ? name : "未知文件", "utf-8"));
            // 创建输出对象
            OutputStream os = response.getOutputStream();
            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            fis.close();
        }
    }
}
