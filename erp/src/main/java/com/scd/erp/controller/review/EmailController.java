package com.scd.erp.controller.review;

import com.scd.erp.mapper.FileMapper;
import com.scd.erp.service.review.ToEmailService;
import com.scd.erp.user.review.ContentFile;
import com.scd.erp.user.review.ToEmail;
import com.scd.erp.utils.MailUtil.JsonReturn;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("mail/")
@RestController
public class EmailController {

    @Autowired
    private ToEmailService toEmailService;
    private final FileMapper fileMapper;

    @Autowired
    public EmailController( FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }


    /**
     * 测试普通邮件发送
     */
    @RequestMapping(value = "/common.do",method = RequestMethod.POST)
    public JsonReturn common(ToEmail toEmail) {
        return toEmailService.commonEmail(toEmail);
    }

    /**
     * html 类型邮件发送
     */
    @PostMapping("/html")
    public JsonReturn html(ToEmail toEmail) throws MessagingException {
        return toEmailService.htmlEmail(toEmail);
    }

    /**
     * 发送附件 邮件发送
     */
    @PostMapping("enclosure")
    public JsonReturn enclosureEmail(ToEmail toEmail, Integer rfid) {
        ContentFile contentFile = fileMapper.selectByPrimaryKey(rfid);
        String url = contentFile.getUrl();
        String[] split = url.split("/");
        String fileNameNow=split[split.length-1];
        String upFile = null;
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            upFile = new File(path.getAbsolutePath(), "static/RecordFile/").toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String newUrl = upFile+"\\"+fileNameNow;
        File file = new File(newUrl);

        FileInputStream input = null;
        MultipartFile multipartFile = null;
        multipartFile = getMultipartFile(file, multipartFile);
        return toEmailService.enclosureEmail(toEmail, multipartFile);
    }

    /**
     * 发送包含静态资源的文件
     *
     * @param toEmail 接收方
     * @param rfids 静态资源
     * @param resId 资源唯一Id(随意传，唯一即可）
     */
    @PostMapping("static")
    public JsonReturn Static(ToEmail toEmail,  String rfids, String resId) {
        String[] rfid = rfids.split(",");
        List list = new ArrayList();
        List list1 = new ArrayList();
        for(int i=0;i<rfid.length;i++ ){
            ContentFile contentFile = fileMapper.selectByPrimaryKey(rfid[i]);
            String url = contentFile.getUrl();
            String[] split = url.split("/");
            String fileNameNow=split[split.length-1];
            String upFile = null;
            try {
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                //upFile = new File(path.getAbsolutePath(), "static/RecordFile/").toString();
               upFile = new File("C://erp/static/RecordFile/").toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String newUrl = upFile+"\\"+fileNameNow;
            list.add(newUrl);
            System.out.println("文件名="+contentFile.getFilename());
            list1.add(contentFile.getFilename());

        }
//        FileInputStream input = null;
//        MultipartFile multipartFile = null;
//        multipartFile = getMultipartFile(file, multipartFile);
        return toEmailService.staticEmail(toEmail, list,list1, resId);
    }

    private MultipartFile getMultipartFile(File file, MultipartFile multipartFile) {
        FileInputStream input;
        try {
            input = new FileInputStream(file);
            try {
                multipartFile =new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return multipartFile;
    }
}
