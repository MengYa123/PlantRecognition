package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.MapValueComparator;
import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.History;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.entity.Recommend;
import com.vegetablechicken.plantrecognition.entity.Thought;
import com.vegetablechicken.plantrecognition.repository.HistoryRepository;
import com.vegetablechicken.plantrecognition.repository.PlantRepository;
import com.vegetablechicken.plantrecognition.repository.RecommendRepository;
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

    @Resource
    private RecommendRepository recommendRepository;

    @Resource
    private PlantRepository plantRepository;

    @Autowired
    private PlantService plantService;

    Random ra =new Random();


    public String insertHistory(String email,long pid, String name,String kind,String pic){
        History history=History.builder().email(email).pid(pid).name(name).kind(kind).pic(pic).build();
        historyRepository.save(history);
        System.out.println(history);

        return "success";
    }

    public List<History> getHistories(String email){
        return historyRepository.findByEmailOrderByHidDesc(email);
    }

    @Transactional
    public String deleteThought(String email,long hid){
        if(historyRepository.findByHid(hid).getEmail().equals(email)) {
            historyRepository.deleteByHid(hid);
            return "delete succeed";
        }else{
            return "delete failed";
        }
    }

    public void updateRecommend(String email){
        List<History> histories=historyRepository.findByEmailOrderByHidDesc(email);
        System.out.println(histories.toString());
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


        List<ReducePlantsResponse> res=new ArrayList<ReducePlantsResponse>();
        Iterator<Map.Entry<String, Integer>> temp = sortedMap.entrySet().iterator();

        String recommendString="";
        for (int i=0;i<sortedMap.size();i++) {
            String recommendRoot =temp.next().getKey();

            recommendRoot=recommendRoot.substring(0, recommendRoot.length() - 1);
            System.out.println(recommendRoot);
            List<ReducePlantsResponse> recommendList= plantService.searchPlant(recommendRoot);

            //System.out.println(index);

            recommendString+=recommendList.get(ra.nextInt(recommendList.size()-1)+1).getKind();
            recommendString+=",";
        }
        System.out.println(recommendString);
        Recommend recommend=Recommend.builder().email(email).recommendlist(recommendString).build();
        recommendRepository.save(recommend);
    }

    public List<ReducePlantsResponse> recommendPlants(String email,int count){
        Optional<Recommend> r=recommendRepository.findById(email);
        System.out.println(r.isPresent());
        if(!r.isPresent()) {
            Pageable pr = PageRequest.of(ra.nextInt(30),count, Sort.Direction.DESC,"pid");
            Page<Plant> page= plantRepository.findAll(pr);
            List<Plant> Plants=page.getContent();
            return Method.ReducePlant(Plants);
        }
        Recommend recommend=r.get();
        String recommendlist=recommend.getRecommendlist();
        String[] kinds = recommendlist.split(",");
        List<Plant> recommendPlants=new ArrayList<Plant>();
        for (int i=0;i<kinds.length&&i<count;i++){
            List<Plant> rep=plantRepository.findByKind(kinds[i]);
            recommendPlants.add(rep.get(ra.nextInt(rep.size()-1)+1));
        }

        return Method.ReducePlant(recommendPlants);

    }

}
