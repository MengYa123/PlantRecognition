package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Plantimage;
import com.vegetablechicken.plantrecognition.repository.PlantimageRepository;
import com.vegetablechicken.plantrecognition.response.RecognitionResultResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        pic = "fileUpload/" + pic.split("/")[pic.split("/").length - 1];
        String pyFileAddress = "/plant";
        String exe = "python3";
        String pyFileAddr = pyFileAddress+"/runable.py";
        String arg1 = pic;
        String[] commandLine = new String[] { exe, pyFileAddr, arg1,pyFileAddress };

        try {
            Process process = Runtime.getRuntime().exec(commandLine);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str = in.readLine();

            in.close();
            process.waitFor();
            System.out.println(str);
            return str;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
