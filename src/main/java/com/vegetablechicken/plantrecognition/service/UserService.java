package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
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
        User newuser=User.builder().email(username).password(password).avatar("http://119.23.231.17:8080/images/32314d11-cbeb-440b-abb0-546ff96bdf7d_d4.png").name("用户_"+ Method.getRandomUUid()).build();
        System.out.println(newuser);
        userRepository.save(newuser);
        return "success";
    }

    public String updateName(String email,String name){
        Optional<User> user=userRepository.findById(email);
        if(!user.isPresent()) return "failed";
        user.get().setName(name);
        userRepository.save(user.get());
        return "update succeed";

    }

    public String updateSignature(String email,String signature){
        Optional<User> user=userRepository.findById(email);
        if(!user.isPresent()) return "failed";
        user.get().setSignature(signature);
        userRepository.save(user.get());
        return "update succeed";

    }

    public String updatePassword(String email,String Password){
        Optional<User> user=userRepository.findById(email);
        if(!user.isPresent()) return "failed";
        user.get().setPassword(Password);
        userRepository.save(user.get());
        return "update succeed";

    }

    public String updateAvatar(String email,String Avatar){
        Optional<User> user=userRepository.findById(email);
        if(!user.isPresent()) return "failed";
        user.get().setAvatar(Avatar);
        userRepository.save(user.get());
        return "update succeed";

    }

    public String updateBackground(String email,String background){
        Optional<User> user=userRepository.findById(email);
        if(!user.isPresent()) return "failed";
        user.get().setBackground(background);
        userRepository.save(user.get());
        return "update succeed";
    }

    public Optional<User> getInfo(String email){
        return userRepository.findById(email);
    }
}