package com.scd.erp.controller.A_Public;

import com.scd.erp.Vo.commen.Template;
import com.scd.erp.mapper.TemplateMapper;
import com.scd.erp.utils.common.FileUtils;
import com.scd.erp.utils.common.ReMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("temp")
public class TemplateController {

    @Autowired private FileUtils fileUtils;
    @Autowired private ReMsgUtil reMsgUtil;
    @Autowired private TemplateMapper templateMapper;

    private static final String secDomain = "templateFile";

    @PostMapping("add.do")
    public Map addTemp(Template template, MultipartFile file){
        String filename = file.getOriginalFilename();
        String suffix = filename.split(".")[1];
        String path = UUID.randomUUID().toString() + "." + suffix;
        fileUtils.addFile(file, path,secDomain);

       template.setTepname(filename);
       template.setTepsize((int)file.getSize());
       template.setTepsuffix(suffix);
       template.setTeppath("/" + secDomain + "/"+path+"."+suffix);
       this.templateMapper.insertSelective(template);
       return reMsgUtil.msg(template,"ok",0);
    }

    @GetMapping("down.do")
    public void downTemp(Integer tempid, HttpServletResponse resp){
        Template template = this.templateMapper.selectByPrimaryKey(tempid);
        fileUtils.download(template.getTepname(),template.getTeppath(),resp);
    }

    @GetMapping("select.do")
    public Map selectTemplate(){
       return reMsgUtil.msg(this.templateMapper.selectAll(),"ko",0);
    }

}
