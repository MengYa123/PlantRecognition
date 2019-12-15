package com.vegetablechicken.plantrecognition.Method;

import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import com.vegetablechicken.plantrecognition.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Method {

    @Autowired
    private static PlantService plantService;

    public static String getRandomUUid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String uploadPic(MultipartFile file){
        if(file.isEmpty()){
            return "上传文件为空";
        }
        String url;

        String fileName=file.getOriginalFilename();
        fileName=getRandomUUid()+"_"+fileName;
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
            url="http://119.23.231.17:8080/images/"+fileName;
            return url;

        } catch (IOException e) {
            e.printStackTrace();
            return "上传错误";
        }
    }

    public static List<ReducePlantsResponse> ReducePlant(List<Plant> plants){
        List<ReducePlantsResponse> reducePlantsResponses= new ArrayList<>();
        for (Plant temp : plants) {
            ReducePlantsResponse re = new ReducePlantsResponse(temp.getPid(), temp.getName(), temp.getPic(), temp.getKind());
            reducePlantsResponses.add(re);
            System.out.println(reducePlantsResponses.get(0));
        }
        //
        return reducePlantsResponses;
    }
    public static List<Plant> searchPlant(String name){
        return plantService.getPlantData(name);
    }
}
