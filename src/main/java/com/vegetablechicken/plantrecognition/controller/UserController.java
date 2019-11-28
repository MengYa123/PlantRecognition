package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.entity.User;
import com.vegetablechicken.plantrecognition.repository.UserRepository;
import com.vegetablechicken.plantrecognition.request.UserRequest;
import com.vegetablechicken.plantrecognition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("login")
    private String login(@RequestBody UserRequest userRequest){
        return userService.login(userRequest.getUserId(),userRequest.getPassword());
    }
}
