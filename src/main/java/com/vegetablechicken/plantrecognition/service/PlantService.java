package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.repository.PlantRepository;
import com.vegetablechicken.plantrecognition.response.PlantSearchResponse;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        return Method.ReducePlant(plants);
    }

    public List<Plant> getPlantData(String name){
        String url = String.format("http://db.kib.ac.cn/CNFlora/SearchEngine.aspx?q=%s", URLEncoder.encode(name));
        List<Plant> plantList = new ArrayList<>();
        Plant plant;
        try {
            Document document = Jsoup.connect(url).get();
            for (Element element: document.getElementsByTag("tbody").get(0).children()){
                if (element.children().get(0).text().equals("所在卷")){
                    continue;
                }
                String detail = getPlantDetail(element.children().get(4).child(0).attr("href"));
                plant = new Plant();
                plant.setKind(element.children().get(1).text());
                plant.setName(element.children().get(3).text());
                plant.setDetail(detail);
                plantList.add(plant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plantList;
    }
    public String getPlantDetail(String url){
        url = String.format("http://db.kib.ac.cn/CNFlora/%s",url);
        Connection connection = Jsoup.connect(url);
        String result = null;
        try {
            result = connection.get().getElementById("ContentPlaceHolder1_lbl_fulltext").text().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
