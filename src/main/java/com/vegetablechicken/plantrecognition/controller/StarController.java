package com.vegetablechicken.plantrecognition.controller;

import com.vegetablechicken.plantrecognition.entity.User;
import com.vegetablechicken.plantrecognition.service.StarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//返回json数据不需要加@ResponseBody
@Slf4j
@Api("关注模块")
@RequestMapping("/star")
public class StarController {

    @Autowired
    private StarService starService;

    @GetMapping("/getFollower")
    @ApiOperation(value = "获取粉丝列表", notes = "获取粉丝列表\n\r成功会返回所有的粉丝的除了密码之外的信息", tags = "Star", httpMethod = "GET")
    public List<User> getFollower(@RequestParam("userid") String userId){
        return starService.getFollowerList(userId);
    }

    @GetMapping("/getStarList")
    @ApiOperation(value = "获取关注的用户列表", notes = "获取关注的用户列表\n\r成功会返回所有的用户的除了密码之外的信息", tags = "Star", httpMethod = "GET")
    public List<User> getStaredUser(@RequestParam("userid") String userId){
        return starService.getStarList(userId);
    }

    @GetMapping("/star")
    @ApiOperation(value = "关注对应的用户", notes = "关注对应的用户\n\r返回对应的状态信息", tags = "Star", httpMethod = "GET")
    public String star(@RequestParam("userid") String userId, @RequestParam("secondUserid") String secondUserId){
        return starService.starSomeone(userId, secondUserId);
    }


    @GetMapping("/unstar")
    @ApiOperation(value = "取消关注对应的用户\", notes = \"关注对应的用户\\n\\r返回对应的状态信息", tags = "Star", httpMethod = "GET")
    public String unstar(@RequestParam String userId, @RequestParam("secondUserid") String secondUserId){
        return starService.unstarSomeone(userId, secondUserId);
    }






}
