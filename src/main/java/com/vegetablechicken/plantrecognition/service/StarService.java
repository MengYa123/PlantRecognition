package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.entity.Star;
import com.vegetablechicken.plantrecognition.entity.User;
import com.vegetablechicken.plantrecognition.repository.StarRepository;
import com.vegetablechicken.plantrecognition.repository.UserRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class StarService {
    @Autowired
    private StarRepository starRepository;
    @Autowired
    private UserRepository userRepository;

    public String starSomeone(String firstUserId,String secondUserId){
        if (starRepository.findByUserIdFirstAndAndUserIdSecond(firstUserId, secondUserId) != null){
            return "star already!";
        }
        Star star = new Star(firstUserId,secondUserId);
        starRepository.save(star);
        return "star " + secondUserId + " success!";
    }

    public String unstarSomeone(String firstUserId,String secondUserId){
        Star star = starRepository.findByUserIdFirstAndAndUserIdSecond(firstUserId, secondUserId);
        if (starRepository.findByUserIdFirstAndAndUserIdSecond(firstUserId, secondUserId) == null){
            return "not stared yet!";
        }
        starRepository.delete(star);
        return "unstar " + secondUserId + " success!";
    }

    public List<User> getStarList(String userId){
        List<Star> starList = starRepository.findAllByUserIdFirst(userId);
        return getUsersInfoList(starList);
    }

    public List<User> getFollowerList(String userId){
        List<Star> starList = starRepository.findAllByUserIdSecond(userId);
        return getUsersInfoList(starList);
    }

    public List<User> getUsersInfoList(List<Star> starList){
        List<User> userIdList = new ArrayList<>();
        starList.forEach(star -> {
            User user = userRepository.findByUserid(star.getUserIdSecond());
            user.setPassword(null);
            userIdList.add(user);
        });
        return userIdList;
    }
}
