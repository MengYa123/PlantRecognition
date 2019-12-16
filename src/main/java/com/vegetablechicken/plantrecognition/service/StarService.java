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

    public String starSomeone(String firstEmail,String secondEmail){
        if (starRepository.findByEmailFirstAndAndEmailSecond(firstEmail, secondEmail) != null){
            return "star already!";
        }
        Star star = new Star(firstEmail,secondEmail);
        starRepository.save(star);
        return "star " + secondEmail + " success!";
    }

    public String unstarSomeone(String firstEmail,String secondEmail){
        Star star = starRepository.findByEmailFirstAndAndEmailSecond(firstEmail, secondEmail);
        if (starRepository.findByEmailFirstAndAndEmailSecond(firstEmail, secondEmail) == null){
            return "not stared yet!";
        }
        starRepository.delete(star);
        return "unstar " + secondEmail + " success!";
    }

    public List<User> getStarList(String email){
        List<Star> starList = starRepository.findAllByEmailFirst(email);
        return getUsersInfoList(starList);
    }

    public List<User> getFollowerList(String email){
        List<Star> starList = starRepository.findAllByEmailSecond(email);
        return getUsersInfoList(starList);
    }

    public List<User> getUsersInfoList(List<Star> starList){
        List<User> emailList = new ArrayList<>();
        starList.forEach(star -> {
            User user = userRepository.findByEmail(star.getEmailSecond());
            user.setPassword(null);
            emailList.add(user);
        });
        return emailList;
    }
}
