package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.entity.Thought;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import com.vegetablechicken.plantrecognition.service.PlantService;
import com.vegetablechicken.plantrecognition.service.PlantimageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api("植物")
@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    private PlantService plantService;

    @GetMapping("/getPlant")
    @ApiOperation(value = "得到植物", notes = "通过pid得到一个植物详细信息", tags = "Plant",httpMethod = "GET")
    public Plant getPlant(@RequestParam String email,@RequestParam long pid){
        return plantService.getPlant(email,pid);
    }
/*
    @GetMapping("/getPlantByKind")
    @ApiOperation(value = "得到植物", notes = "通过类别得到推荐植物简略信息", tags = "Plant",httpMethod = "GET")
    public List<ReducePlantsResponse> getPlantByKind(@RequestParam String email, @RequestParam String kind,@RequestParam int count){

        return plantService.getPlantByKind(email,kind,count);
    }

    @GetMapping("/getKinds")
    @ApiOperation(value = "得到类别", notes = "得到推荐类别", tags = "Plant",httpMethod = "GET")
    public List<ReducePlantsResponse> getKinds(@RequestParam String email, @RequestParam int count){

        return plantService.getRecommendKind(email,count);
    }*/

    @GetMapping("/getPlantInfo")
    @ApiOperation(value = "得到植物", notes = "通过植物名得到植物信息", tags = "Plant", httpMethod = "GET")
    public List<ReducePlantsResponse> getPlantInfo(@RequestParam String name){
        return plantService.searchPlant(name);
    }
}
