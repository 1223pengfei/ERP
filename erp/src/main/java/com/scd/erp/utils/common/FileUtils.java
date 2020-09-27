package com.scd.erp.utils.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class FileUtils {

    @Value("${isDevelop}")
    private boolean isdev;



    public  void download(String filename,String passFix, HttpServletResponse res){

        download(filename,passFix,res,1024);
    }

    public  void download(String filename,String passFix, HttpServletResponse res,int cacheSize) {
            try {                                      /*classpath*/
                System.out.println(isdev);
                URL url  = ResourceUtils.getURL(isdev?"":"classpath:");

                String filePath = url.getPath()+ passFix;
                File file = new File(filePath);

                FileInputStream fis = new FileInputStream(file);

                res.setContentType("application/force-download");
                res.setHeader("Content-Type", "application/octet-stream;charset=utf-8");

                res.addHeader("Content-disposition", "attachment;fileName=" +
                        URLEncoder.encode(filename, "utf-8"));

                OutputStream os = res.getOutputStream();

                byte[] buf = new byte[cacheSize];
                int len;
                while ((len = fis.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                fis.close();

        }catch(Exception e){
            e.printStackTrace();
        }


    }


    public File handlePath(String secDomain,String filename ) throws FileNotFoundException {

        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }

        File upFile = new File(path.getAbsolutePath(), "static/" + secDomain + "/");
        if (!upFile.exists()) {
            upFile.mkdirs();
        }
        return new File(path.getAbsolutePath(), "/static/" + secDomain + "/" + filename);

    }
    public void addFile(MultipartFile uploadFile, String filename ,String secDomain) {
        try {
            File dir = handlePath(secDomain, filename);
            //System.out.println(dir.getPath());
            uploadFile.transferTo(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
