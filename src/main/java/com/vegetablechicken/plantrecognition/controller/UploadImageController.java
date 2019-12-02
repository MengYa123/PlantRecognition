package com.vegetablechicken.plantrecognition.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Api("图片")
@RestController
@RequestMapping("/images")
public class UploadImageController {



    @PostMapping(value="/uploadFile")
    @ApiOperation(value = "图片",notes = "上传图片", tags = "IMAGE")
    public String uploadFile(@RequestParam("fileName")MultipartFile file){
        if(file.isEmpty()){
            return "上传文件为空";
        }
        String url;

        String fileName=file.getOriginalFilename();
        fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+fileName;
        String path="./fileUpload/"+fileName;
        File destination =new File(path);
        if(destination.exists()){
            return "文件已经存在";
        }

        if(!destination.getParentFile().exists()){
            destination.getParentFile().mkdir();
        }
        try{

            file.transferTo(destination.getAbsoluteFile());
            url="http://127.0.0.1:8080/images/"+fileName;
            return url;

        } catch (IOException e) {
            e.printStackTrace();
            return "上传错误";
        }
    }
}
