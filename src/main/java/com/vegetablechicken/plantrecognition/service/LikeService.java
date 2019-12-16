package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Comment;
import com.vegetablechicken.plantrecognition.entity.Like;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.repository.CommentRepository;
import com.vegetablechicken.plantrecognition.repository.LikeRepository;
import com.vegetablechicken.plantrecognition.repository.PlantRepository;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LikeService {
    @Resource
    private LikeRepository likeRepository;
    @Resource
    private PlantRepository plantRepository;

    public String insertLike(String email,long pid){
        Like like=Like.builder().pid(pid).email(email).build();
        likeRepository.save(like);
        return "success";
    }


    @Transactional
    public void deleteLike(String email,long pid){
        likeRepository.deleteByEmailAndPid(email,pid);

    }

    public List<Like> getLikes(String email){
        return likeRepository.findByEmail(email);
    }



    public List<ReducePlantsResponse> getLikePlants(String email) {
        List<Plant> plants=new ArrayList<Plant>();
        List<Like> likes=likeRepository.findByEmail(email);
        for (int i=0;i<likes.size();i++){
            Plant plant=plantRepository.findByPid(likes.get(i).getPid());
            plants.add(plant);
        }
        return Method.ReducePlant(plants);
    }

}
