package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Plantimage;
import com.vegetablechicken.plantrecognition.repository.PlantimageRepository;
import com.vegetablechicken.plantrecognition.response.RecognitionResultResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PlantimageService {
    @Resource
    private PlantimageRepository plantimageRepository;

    public RecognitionResultResponse insertPlantimage(String pic){
        Plantimage plantimage= Plantimage.builder().piid(Method.getRandomUUid()).pic(pic).build();
        plantimage.setName(Identify(pic));
        plantimageRepository.save(plantimage);
        RecognitionResultResponse recognitionResultResponse=RecognitionResultResponse.builder().pic(pic).name(plantimage.getName()).build();
        return recognitionResultResponse;
    }

    public String Identify(String pic){
        //do sth
        return "识别的接口还没有";
    }
}
