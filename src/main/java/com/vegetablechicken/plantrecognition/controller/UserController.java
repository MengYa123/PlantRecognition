package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.User;
import com.vegetablechicken.plantrecognition.request.UserInfoRequest;
import com.vegetablechicken.plantrecognition.request.UserRequest;
import com.vegetablechicken.plantrecognition.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Api("用户操作")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录\n\rlogin succeed\n\rPassword error\n\rNo registration", tags = "User",httpMethod = "POST")
    public String login(@RequestBody UserRequest userRequest){
        //log.info("login:{}",userRequest);
        return userService.login(userRequest.getUserId(),userRequest.getPassword());
    }

    @PostMapping("/signup")
    @ApiOperation(value = "用户注册", notes = "用户注册\n\rAlready registered\n\rsuccess", tags = "User",httpMethod = "POST")
    public String signup(@RequestBody UserRequest userRequest){
        return userService.signup(userRequest.getUserId(),userRequest.getPassword());
    }

    @GetMapping("/getInfo")
    @ApiOperation(value = "获得信息", notes = "获得信息", tags = "User",httpMethod = "GET")
    public Optional<User> getInfo(@RequestParam String userid){
        return userService.getInfo(userid);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码", notes = "修改密码\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String updateInfo(@RequestBody UserRequest userRequest){
        return userService.updatePassword(userRequest.getUserId(),userRequest.getPassword());
    }

    @PostMapping("/updateInfo")
    @ApiOperation(value = "修改信息", notes = "修改信息\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String updateInfo(@RequestBody UserInfoRequest userInfoRequest){
        return userService.updatePassword(userInfoRequest.getUserid(),userInfoRequest.getName());
    }



    @PostMapping("/updateAvatar")
    @ApiOperation(value = "修改头像", notes = "修改头像\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String uploadAvatar(@RequestParam("userid")String userid,@RequestParam("file") MultipartFile file){
        return userService.updateAvatar(userid,Method.uploadPic(file));
    }


}
