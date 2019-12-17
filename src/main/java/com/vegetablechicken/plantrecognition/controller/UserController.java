package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.User;
import com.vegetablechicken.plantrecognition.request.*;
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
        return userService.login(userRequest.getEmail(),userRequest.getPassword());
    }

    @PostMapping("/signup")
    @ApiOperation(value = "用户注册", notes = "用户注册\n\rAlready registered\n\rsuccess", tags = "User",httpMethod = "POST")
    public String signup(@RequestBody UserRequest userRequest){
        return userService.signup(userRequest.getEmail(),userRequest.getPassword());
    }

    @GetMapping("/getInfo")
    @ApiOperation(value = "获得信息", notes = "获得信息", tags = "User",httpMethod = "GET")
    public Optional<User> getInfo(@RequestParam String email){
        return userService.getInfo(email);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码", notes = "修改密码\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String updatePassword(@RequestBody UserRequest userRequest){
        return userService.updatePassword(userRequest.getEmail(),userRequest.getPassword());
    }

    @PostMapping("/updateName")
    @ApiOperation(value = "修改昵称", notes = "修改昵称\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String updateName(@RequestBody UserNameRequest userNameRequest){
        return userService.updateName(userNameRequest.getEmail(),userNameRequest.getName());
    }

    @PostMapping("/updateSignature")
    @ApiOperation(value = "修改签名", notes = "修改签名\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String updateSignature(@RequestBody UserSignatureRequset userSignatureRequset){
        return userService.updateSignature(userSignatureRequset.getEmail(),userSignatureRequset.getSignature());
    }



    @PostMapping("/updateAvatar")
    @ApiOperation(value = "修改头像", notes = "修改头像\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String uploadAvatar(@RequestBody UserAvatarRequest userAvatarRequest){
        return userService.updateAvatar(userAvatarRequest.getEmail(),userAvatarRequest.getAvatar());
    }

    @PostMapping("/updateBackground")
    @ApiOperation(value = "修改背景图", notes = "修改背景图\n\rupdate succeed\n\rfailed", tags = "User",httpMethod = "POST")
    public String updateBackground(@RequestBody UserBackgroundRequest userBackgroundRequest){
        return userService.updateBackground(userBackgroundRequest.getEmail(),userBackgroundRequest.getBackground());
    }


}
