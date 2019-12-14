package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.repository.PlantRepository;
import com.vegetablechicken.plantrecognition.response.PlantSearchResponse;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlantService {
    @Resource
    private PlantRepository plantRepository;

    @Autowired
    private HistoryService historyService;

    public PlantSearchResponse searchPlant(String userid,String name){
        //do sth
        return null;
    }

    public Plant getPlant(String userid,long pid){
        Plant plant=plantRepository.findByPid(pid);
        historyService.insertHistory(userid,plant.getPid(),plant.getName(),plant.getKind(),plant.getPic());//添加到查询历史记录里面
        return plant;
    }

    public List<Plant> getPlantByKind(String userid,String kind){
        return plantRepository.findByKind(kind);
    }

    public List<ReducePlantsResponse> getSimplePlantByKind(String userid, String kind){
        List<Plant> plants=plantRepository.findByKind(kind);
        List<ReducePlantsResponse> reducePlantsResponses=Method.ReducePlant(plants);
        return reducePlantsResponses;
    }

}
