package com.scd.erp.service.review.Impl;

import java.io.File;

import com.scd.erp.service.review.ToEmailService;
import com.scd.erp.user.review.ToEmail;
import com.scd.erp.utils.MailUtil.JsonReturn;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author leilei
 * @version 1.0
 * @date 2020/3/12 14:37
 * @desc: 邮件发送支持 实现类
 */
@Service
@Slf4j
public class ToEmailServiceImpl implements ToEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送普通邮件
     *
     * @param toEmail 发送对象
     * @return 统一ajax
     */
    @Override
    public JsonReturn commonEmail(@RequestBody ToEmail toEmail) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
            return JsonReturn.buildSuccess(toEmail.getTos(), "发送普通邮件成功");
        } catch (MailException e) {
            e.printStackTrace();
            return JsonReturn.buildFailure("普通邮件方失败");
        }
    }

    /**
     * 发送Html 有机胺
     * 有机胺
     *
     * @param toEmail 发送对象
     */
    @Override
    public JsonReturn htmlEmail(ToEmail toEmail) throws MessagingException {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
        //谁发
        minehelper.setFrom(from);
        //谁要接收
        minehelper.setTo(toEmail.getTos());
        //邮件主题
        minehelper.setSubject(toEmail.getSubject());
        //邮件内容   true 表示带有附件或html
        minehelper.setText(toEmail.getContent(), true);
        try {
            mailSender.send(message);
            return JsonReturn.buildSuccess(toEmail.getTos() + toEmail.getContent(), "HTML邮件成功");
        } catch (MailException e) {
            e.printStackTrace();
            return JsonReturn.buildFailure("HTML邮件失败");
        }
    }


    /**
     * 带附件邮件发送
     */
    @Override
    public JsonReturn enclosureEmail(ToEmail toEmail, MultipartFile multipartFile) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //谁发
            helper.setFrom(from);
            //谁接收
            helper.setTo(toEmail.getTos());
            //邮件主题
            helper.setSubject(toEmail.getSubject());
            //邮件内容   true 表示带有附件或html
            helper.setText(toEmail.getContent(), true);
            File multipartFileToFile = MultipartFileToFile(multipartFile);
            FileSystemResource file = new FileSystemResource(multipartFileToFile);
            String filename = file.getFilename();
            helper.addAttachment(filename, file);//添加附件
            mailSender.send(message);
            return JsonReturn.buildSuccess(toEmail.getTos() + toEmail.getContent(), "附件邮件成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            return JsonReturn.buildFailure("附件邮件发送失败" + e.getMessage());
        }
    }

    /**
     * 发送静态资源
     *
     * @param resId 每个资源对饮给一个Id
     */
    public JsonReturn staticEmail(ToEmail toEmail, List list,List list1, String resId) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        String[] tos = toEmail.getTos();
        for (int i = 0; i < tos.length; i++) {
            String newtos = tos[i];
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                //谁发
                helper.setFrom(from);
                //谁接收
                helper.setTo(newtos);
                //邮件主题
                helper.setSubject(toEmail.getSubject());
                //邮件内容   true 表示带有附件或html
                String content1 = toEmail.getContent();
                String[] split = content1.split(",");
                String content=null;
                for (int j=0;j<split.length;j++) {
                    String newContent = split[i];
                    //System.out.println("新闻本内容="+newContent);
                    //邮件内容拼接
                    content = "<html>\n" +
                            "<body>\n" +
                            "<h4>尊敬的各位领导、同事:（"+"<font color=\"red\">本邮件由项目管理系统自动发出</font>）</h4>\n" +
                            "以下链接为公司自主开发的项目管理系统，为良好的结合公司运营需求，因此导入APQP流程方案。\n" +
                            "目前该项目为测试阶段，请收到本邮件领导及同仁点击以下链接，进行评审测试，并对本次的内容提供建议。非常感谢！</h4>" +
                            "<h4>系统链接:"+"<a href=" + newContent + ">链接地址</a></h4>\n" +
                            "<h4>使用说明:</h4>\n" +
                            "   本邮件是项目经理在建立项目评审单时给各个评审人员发送的，附件中是评审需要的文件，包括但不限于：说明文档、PDF文档、项目图纸等；\n" +
                            "点击链接地址，可以直接到项目评审单页面，在该页面-》您的评审意见中，可以输入您想写的评审意见，点击提交就可以了，同时会签中就有了您的签名及时间，\n" +
                            "这样您的评审内容就提交到后台了，项目经理可以看到。此外，也欢迎各部门提供订制需求。\n" +
                            "如在使用的过程中遇到问题，可直接回复邮件。\n" +
                            "<h4>---------------------------------------------------------</h4>\n" +
                            "兴科迪研究院 项目管理系统 <br>" +
                            "北京兴科迪科技有限公司<br>" +
                            "北京市海淀区清华科技园玉泉慧谷16栋<br>" +
                            "Email：tech@sincode.com<br>" +
                            "系统登录位置："+"<a href=http://139.129.92.198:8088/index.html#>http://139.129.92.198:8088/index.html#</a><br>" +
                            "</body>\n" +
                            "</html>";
                    break;

                }
                helper.setText(content, true);
//                File multipartFileToFile = MultipartFileToFile(multipartFile);
//                FileSystemResource file = new FileSystemResource(multipartFileToFile);
//                String filename = file.getFilename();
                FileSystemResource file=null;
                Iterator iter = list.iterator();
                Iterator iter1 = list1.iterator();
                while (iter.hasNext() & iter1.hasNext()){
                    String url = (String) iter.next();
                    String filename = (String) iter1.next();
                    file=new FileSystemResource(url);
                    helper.addAttachment(filename, file);

                }
                //helper.addAttachment(filename, file);//添加附件
                mailSender.send(message);
            } catch (MessagingException e) {
                return JsonReturn.buildFailure("嵌入静态资源的邮件发送失败");
            }
        }
        return JsonReturn.buildSuccess(toEmail.getTos() + toEmail.getContent(), "嵌入静态资源的邮件已经发送");
    }


    /**
     * 将 multpartfile 转为file
     *
     * @return file
     */
    private File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}