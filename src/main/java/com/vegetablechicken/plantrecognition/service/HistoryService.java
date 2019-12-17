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
import java.util.stream.Stream;


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

    Random ra = new Random();


    public String insertHistory(String email, long pid, String name, String kind, String pic) {
        History history = History.builder().email(email).pid(pid).name(name).kind(kind).pic(pic).build();
        historyRepository.save(history);
        System.out.println(history);

        return "success";
    }

    public List<History> getHistories(String email) {
        return historyRepository.findByEmailOrderByHidDesc(email);
    }

    @Transactional
    public String deleteThought(String email, long hid) {
        if (historyRepository.findByHid(hid).getEmail().equals(email)) {
            historyRepository.deleteByHid(hid);
            return "delete succeed";
        } else {
            return "delete failed";
        }
    }

    public void updateRecommend(String email) {
        List<History> histories = historyRepository.findByEmailOrderByHidDesc(email);
        System.out.println(histories.toString());
        Map<String, Integer> map = new HashMap<>();
        for (History history : histories) {
            String kind = history.getKind();
            if (map.containsKey(kind)) {
                int num = map.get(kind);
                map.put(kind, num + 1);
            } else {
                map.put(kind, 1);
            }
        }

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = list.iterator();
        Map.Entry<String, Integer> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        System.out.println(sortedMap.toString());


        List<ReducePlantsResponse> res = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> temp = sortedMap.entrySet().iterator();

        StringBuilder recommendString = new StringBuilder();
        for (int i = 0; i < sortedMap.size(); i++) {
            String recommendRoot = temp.next().getKey();

            recommendRoot = recommendRoot.substring(0, recommendRoot.length() - 1);
            System.out.println(recommendRoot);
            List<ReducePlantsResponse> recommendList = plantService.searchPlant(recommendRoot);

            //System.out.println(index);

            recommendString.append(recommendList.get(ra.nextInt(recommendList.size() - 1) + 1).getKind());
            recommendString.append(",");
        }
        System.out.println(recommendString);
        Recommend recommend = Recommend.builder().email(email).recommendlist(recommendString.toString()).build();
        recommendRepository.save(recommend);
    }

    public List<ReducePlantsResponse> recommendPlants(String email, int count) {
        Optional<Recommend> r = recommendRepository.findById(email);
        System.out.println(r.isPresent());
        if (!r.isPresent()) {
            Pageable pr = PageRequest.of(ra.nextInt(5), count, Sort.Direction.DESC, "pid");
            Page<Plant> page = plantRepository.findAll(pr);
            List<Plant> Plants = page.getContent();

            return Method.ReducePlant(Plants);
        }
        Recommend recommend = r.get();
        String recommendlist = recommend.getRecommendlist();
        String[] kinds = recommendlist.split(",");
        List<Plant> recommendPlants = new ArrayList<>();
        for (int i = 0; i < kinds.length && i < count; i++) {
            List<Plant> rep = plantRepository.findByKind(kinds[i]);
            recommendPlants.add(rep.get(ra.nextInt(rep.size())));
        }

        return Method.ReducePlant(recommendPlants);

    }


    public List<String> recommendPlantsClass(String email, int count) {
        List<History> historyList = historyRepository.findByEmailOrderByHidDesc(email);
        HashMap<String, Integer> orderClassList = new HashMap<>();
        historyList.forEach(history -> {
            if (orderClassList.containsKey(history.getKind())) {
                orderClassList.put(history.getKind(), orderClassList.get(history.getKind()) + 1);
            } else {
                orderClassList.put(history.getKind(), 1);
            }
        });
        List<Map.Entry<String, Integer>> orderedList = orderMap(orderClassList);
        if (orderedList.size() < count){
            count = orderedList.size();
        }
        List<String> recommendClass = new ArrayList<>();
        orderedList.stream().forEach(stringIntegerEntry -> System.out.println(stringIntegerEntry.getKey()));
        orderedList.stream().limit(count).forEach(entry -> recommendClass.add(entry.getKey()));
        return recommendClass;
    }

    public List<Map.Entry<String, Integer>> orderMap(HashMap<String, Integer> initialMap) {
        if (initialMap == null || initialMap.isEmpty()) {
            return new ArrayList<>();
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(initialMap.entrySet());
        Collections.sort(entryList, Comparator.comparingInt(Map.Entry::getValue));
        return entryList;
    }

    public List<ReducePlantsResponse> getRecommendClassPlant(String email, String kind, int count) {
        Random random = new Random();
        List<History> historyList = historyRepository.findByEmailAndKind(email, kind);
        List<ReducePlantsResponse> result = new ArrayList<>();
        if (historyList.size() < count){
            count = historyList.size();
        }
        Stream<History> stream = historyList.stream();
        if (historyList.size() > 2 * count){
            stream = stream.skip(random.nextInt(historyList.size() - count));
        }
        stream.limit(count).forEach(history -> result.add(new ReducePlantsResponse(history.getPid(), history.getName(), history.getPic(), history.getKind())));
        return result;
    }
}
