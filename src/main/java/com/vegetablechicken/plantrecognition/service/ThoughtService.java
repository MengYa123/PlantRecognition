package com.vegetablechicken.plantrecognition.service;


import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Thought;
import com.vegetablechicken.plantrecognition.repository.ThoughtRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

@Service
public class ThoughtService {

    @Resource
    private ThoughtRepository thoughtRepository;


    public String insertThought(String userid, String content,String pic){
        Thought thought=Thought.builder().userid(userid).content(content).pic(pic).build();
        thoughtRepository.save(thought);
        return "success";
    }

    public List<Thought> getThought(String userid){
        return thoughtRepository.findByUseridOrderByTid(userid);
    }
    @Transactional
    public String deleteThought(String userid,long tid){
        if(thoughtRepository.findByTid(tid).getUserid().equals(userid)) {
            thoughtRepository.deleteByTid(tid);
            return "delete succeed";
        }else{
            return "delete failed";
        }
    }

    public List<Thought> getSomeThoughts(int num){
        Pageable pr = PageRequest.of(0,num, Sort.Direction.DESC,"tid");
        Page<Thought> page= thoughtRepository.findAll(pr);
        List<Thought> thoughts=page.getContent();
        return thoughts;
    }





}
