package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.entity.User;
import com.vegetablechicken.plantrecognition.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService  {

    @Resource
    private UserRepository userRepository;

    public String login(String username,String password){
        Optional<User> user=userRepository.findById(username);
        if(!user.isPresent()) return "No registration";
        if(user.get().getPassword().equals(password)){
            return "login succeed";
        }
        else{
            return "Password error";
        }

    }

    public String signup(String username,String password){
        Optional<User> user=userRepository.findById(username);
        if(user.isPresent()) return "Already registered";
        User newuser=User.builder().userid(username).password(password).name("未设置昵称").build();
        userRepository.save(newuser);
        return "success";
    }

    public String updateInfo(String userid,String name){
        Optional<User> user=userRepository.findById(userid);
        if(!user.isPresent()) return "failed";
        user.get().setName(name);
        userRepository.save(user.get());
        return "update succeed";

    }

    public String updatePassword(String userid,String Password){
        Optional<User> user=userRepository.findById(userid);
        if(!user.isPresent()) return "failed";
        user.get().setName(Password);
        userRepository.save(user.get());
        return "update succeed";

    }

    public String updateAvatar(String userid,String Avatar){
        Optional<User> user=userRepository.findById(userid);
        if(!user.isPresent()) return "failed";
        user.get().setName(Avatar);
        userRepository.save(user.get());
        return "update succeed";

    }

    public Optional<User> getInfo(String userid){
        return userRepository.findById(userid);
    }
}
