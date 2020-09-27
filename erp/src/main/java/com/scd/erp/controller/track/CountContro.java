package com.scd.erp.controller.track;

import com.scd.erp.mapper.AlarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("count")
public class CountContro {


    private final AlarmMapper alarmMapper;

    @Autowired
    public CountContro(AlarmMapper alarmMapper) {
        this.alarmMapper = alarmMapper;
    }


    @RequestMapping("oem.do")
    public Map byDate(Integer where) {
        //System.out.println(where);
        Map map = new LinkedHashMap();
        List<Map> maps = this.alarmMapper.countOEM(where);
        String bt = where == 0 ?
                "主机厂问题分布" : where == 1 ?
                "生产线问题分布" : where == 2 ?
                "上报时间分布" : "问题点分布";
        map.put("bt", bt);
        map.put("map", maps);
        return map;
    }


    @PostMapping("fast.do")
    public void fast(HttpServletResponse response, HttpServletRequest request) {
        String ip = null;    //取得服务器IP
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String port = ":" + request.getLocalPort();//取得服务器端口
        String templateContent = "[InternetShortcut]" + "\n" + "URL= http://" + ip + port;
        //System.out.println(templateContent);
        String realfilename = "ERP" + ".url";
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String upurl = fsv.getHomeDirectory().toString();
        //System.out.println(upurl);
        String filename = upurl + "/" + realfilename;
        File myfile = new File(filename);
        try {
            if (!myfile.exists()) {
                FileOutputStream fileoutputstream = new FileOutputStream(filename);//建立文件输出流
                byte tag_bytes[] = templateContent.getBytes();
                fileoutputstream.write(tag_bytes);
                fileoutputstream.close();
            }

            File file = new File(upurl, realfilename);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[111000];
            realfilename = java.net.URLEncoder.encode(realfilename,
                    "UTF-8");
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download");//不同类型的文件对应不同的MIME类型
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + realfilename);
            OutputStream os = response.getOutputStream();
            while (bis.read(buffer) > 0) {
                os.write(buffer);
            }
            bis.close();
            os.close();
//            out.clear();
//            out=pageContext.pushBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
