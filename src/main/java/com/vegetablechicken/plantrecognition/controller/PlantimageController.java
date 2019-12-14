package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Plantimage;
import com.vegetablechicken.plantrecognition.response.RecognitionResultResponse;
import com.vegetablechicken.plantrecognition.service.PlantimageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@Api("识别")
@RestController
@RequestMapping("/Recognitation")
public class PlantimageController {
    @Autowired
    private PlantimageService plantimageService;

    @PostMapping("/PlantIdentification")
    @ApiOperation(value = "植物识别", notes = "植物图片上传，此接口专门用于上传识别的植物图片", tags = "Plantimage",httpMethod = "POST")
    public RecognitionResultResponse PlantIdentification(@RequestParam("file") MultipartFile file){
        return plantimageService.insertPlantimage(Method.uploadPic(file));
    }
}
