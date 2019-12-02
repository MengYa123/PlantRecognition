package com.vegetablechicken.plantrecognition.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    //@Value("${pic.Path}")
    //private String ImagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*if(ImagePath.equals("") || ImagePath.equals("${pic.Path}")){
            String imagesPath = WebMvcConfiguration.class.getClassLoader().getResource("").getPath();

            if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/images/";

            ImagePath = imagesPath;
        }
        System.out.println(ImagePath);*/

        registry.addResourceHandler("/images/**").addResourceLocations("file:./fileUpload/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}

