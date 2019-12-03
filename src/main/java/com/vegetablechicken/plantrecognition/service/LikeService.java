package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Comment;
import com.vegetablechicken.plantrecognition.entity.Like;
import com.vegetablechicken.plantrecognition.repository.CommentRepository;
import com.vegetablechicken.plantrecognition.repository.LikeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikeService {
    @Resource
    private LikeRepository likeRepository;

    public String insertLike(String pid,String userid){
        Like like=Like.builder().pid(pid).userid(userid).build();
        likeRepository.save(like);
        return "success";
    }

    public void deleteLike(String userid,String pid){
        likeRepository.deleteByUseridAndPid(userid,pid);

    }

    public List<Like> getLikes(String userid){
        return likeRepository.findByUserid(userid);
    }

}
