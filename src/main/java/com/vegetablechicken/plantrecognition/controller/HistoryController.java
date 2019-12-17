package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.entity.History;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import com.vegetablechicken.plantrecognition.service.HistoryService;
import com.vegetablechicken.plantrecognition.service.LikeService;
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
@Api("历史记录")
@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/getRecommend")
    @ApiOperation(value = "获取推荐", notes = "根据查看植物的历史记录推荐植物,count为想要推荐的植物个数。", tags = "Recommend",httpMethod = "GET")
    public List<ReducePlantsResponse> getRecommend(@RequestParam String email,@RequestParam int count){

        return historyService.recommendPlants(email,count);
    }

    @GetMapping("/getRecommendClass")
    @ApiOperation(value = "获取对应的推荐类别", notes ="根据查看的历史记录推荐对应的类别", tags = "Recommend", httpMethod = "GET")
    public List<ReducePlantsResponse> getRecommendClass(@RequestParam("email") String email, @RequestParam("count") int count){
        return historyService.recommendPlantsClass(email,count);
    }

    @GetMapping("/getRecommendPlant")
    @ApiOperation(value = "根据类别来进行推荐植物", notes = "根据类别以及用户的浏览记录来进行植物的推荐", tags = "Recommend", httpMethod = "GET")
    public List<ReducePlantsResponse> getRecommendClassPlant(@RequestParam("email") String email, @RequestParam("kind") String kind, @RequestParam("count") int count){
        return historyService.getRecommendClassPlant(email, kind, count);
    }
}
