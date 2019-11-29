package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.request.UserRequest;
import com.vegetablechicken.plantrecognition.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("用户操作")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "用户登录", tags = "User",httpMethod = "POST")
    public String login(@RequestBody UserRequest userRequest){
        //log.info("login:{}",userRequest);
        return userService.login(userRequest.getUserId(),userRequest.getPassword());
    }

    @PostMapping("/signup")
    @ApiOperation(value = "注册", notes = "用户注册", tags = "User",httpMethod = "POST")
    public String signup(@RequestBody UserRequest userRequest){
        return userService.signup(userRequest.getUserId(),userRequest.getPassword());
    }
}
