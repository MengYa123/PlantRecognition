package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.response.RecognitionResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Api("图片")
@RestController
@RequestMapping("/image")
public class ImageUploadController {

    @PostMapping("/upload")
    @ApiOperation(value = "上传图片", notes = "上传图片得到url，此接口用来只用来上传图片（一般用来发布想法时使用），植物识别请用植物识别专用的接口。", tags = "Image",httpMethod = "POST")
    public String PlantIdentification(@RequestParam("file") MultipartFile file){
        return Method.uploadPic(file);
    }
}
