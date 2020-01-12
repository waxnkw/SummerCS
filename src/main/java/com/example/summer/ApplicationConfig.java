package com.example.summer;

import com.example.summer.configInfo.DataAbsolutePath;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        /*
         * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
         * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
         */
        //String path = ApplicationConfig.class.getResource("/").getPath();
        //registry.addResourceHandler("/data/ImageFlow/**").addResourceLocations("http:"+path);
       //registry.addResourceHandler("/data/ImageFlow/**").addResourceLocations("file:"+ DataAbsolutePath.IMAGEFLOW_PATH);

        //阿里云(映射路径去除盘符)
        //registry.addResourceHandler("/ueditor/image/**").addResourceLocations("/upload/image/");
        //registry.addResourceHandler("/ueditor/video/**").addResourceLocations("/upload/video/");
       // super.addResourceHandlers(registry);
    }

}
