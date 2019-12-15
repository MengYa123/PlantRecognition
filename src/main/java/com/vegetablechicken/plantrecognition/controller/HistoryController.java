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
    @ApiOperation(value = "获取推荐", notes = "根据查看植物的历史记录推荐植物,count为想要推荐的植物个数。接口完成一半，注意count不要大于已看过的植物数量", tags = "Recommend",httpMethod = "GET")
    public List<ReducePlantsResponse> getRecommend(@RequestParam String userid,@RequestParam int count){

        return historyService.recommendPlants(userid,count);
    }
}
