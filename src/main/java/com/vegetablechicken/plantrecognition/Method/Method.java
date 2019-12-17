package com.vegetablechicken.plantrecognition.Method;

import com.alibaba.fastjson.JSONObject;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import com.vegetablechicken.plantrecognition.service.PlantService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;
import java.io.File;
import java.io.IOException;
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

    public JSONObject getExternalPlantList(int count){
        String url = String.format("https://api.apishop.net/common/plantFamily/queryPlantList?apiKey=laUuwV4e99fe7400a5ea670e5c6cb78b74c84eeccbe3af4&page=1&pageSize=%d", count);
        try {
            System.out.println(Jsoup.connect(url).get().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
