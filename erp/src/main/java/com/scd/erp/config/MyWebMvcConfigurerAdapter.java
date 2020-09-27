package com.scd.erp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //指向外部目录
        //param1是指定的要上传的文件夹位置（去掉盘符，在最后添加“/**” 例如：“imgUploads/**”）
        //param2是指定的文件夹位置（带盘符，在前面要加上“file” 例如：“file:D:/imgUploads/”）
        registry.addResourceHandler("/ftpfile/**").addResourceLocations("file:C:/ftpfile/");
        super.addResourceHandlers(registry);
    }
}
