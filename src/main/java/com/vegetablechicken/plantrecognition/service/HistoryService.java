package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.MapValueComparator;
import com.vegetablechicken.plantrecognition.entity.History;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.entity.Thought;
import com.vegetablechicken.plantrecognition.repository.HistoryRepository;
import com.vegetablechicken.plantrecognition.repository.ThoughtRepository;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service
public class HistoryService {


    @Resource
    private HistoryRepository historyRepository;

    @Autowired
    private PlantService plantService;


    public String insertHistory(String userid,long pid, String name,String kind,String pic){
        History history=History.builder().userid(userid).pid(pid).name(name).pic(pic).build();
        historyRepository.save(history);
        return "success";
    }

    public List<History> getHistories(String userid){
        return historyRepository.findByUseridOrderByHidDesc(userid);
    }

    @Transactional
    public String deleteThought(String userid,long hid){
        if(historyRepository.findByHid(hid).getUserid().equals(userid)) {
            historyRepository.deleteByHid(hid);
            return "delete succeed";
        }else{
            return "delete failed";
        }
    }

    public List<ReducePlantsResponse> recommendPlants(String userid,int count){
        List<History> histories=historyRepository.findByUseridOrderByHidDesc(userid);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0;i<histories.size();i++){
            String kind=histories.get(i).getKind();
            if(map.containsKey(kind)) {
                int num=map.get(kind);
                map.put(kind,num+1);
            }else {
                map.put(kind,1);
            }
        }
        if(map.size()<count) { //历史记录不够时推荐预先准备的植物
            List<ReducePlantsResponse> res=new ArrayList<ReducePlantsResponse>();
            return null;
        }

        //排序
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());

        Collections.sort(list, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = list.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        System.out.println(sortedMap.toString());


        //推荐
        List<ReducePlantsResponse> res=new ArrayList<ReducePlantsResponse>();
        Iterator<Map.Entry<String, Integer>> temp = sortedMap.entrySet().iterator();
        Random ra =new Random();
        for (int i=0;i<count;i++) {
            String recommendRoot =temp.next().getKey();

            recommendRoot=recommendRoot.substring(0, recommendRoot.length() - 1);
            System.out.println(recommendRoot);
            List<ReducePlantsResponse> recommendList= plantService.searchPlant(recommendRoot);

            //System.out.println(index);
            res.add(recommendList.get(ra.nextInt(recommendList.size()-1)+1));
        }
        return res;
    }


}
