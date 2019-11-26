package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.entity.User;
import com.vegetablechicken.plantrecognition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("hello")
    private String hello(){
        User user = new User();
        return  "hello";
    }
}
