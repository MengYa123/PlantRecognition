package com.vegetablechicken.plantrecognition.service;


import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Thought;
import com.vegetablechicken.plantrecognition.repository.ThoughtRepository;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class ThoughtService {

    @Resource
    private ThoughtRepository thoughtRepository;


    public String insertThought(String userid, String content){
        Thought thought=Thought.builder().tid(Method.getRandomUUid()).userid(userid).content(content).build();
        thoughtRepository.save(thought);
        return "success";
    }

    public List<Thought> getThought(String userid){
        return thoughtRepository.findByUserid(userid);
    }





}
