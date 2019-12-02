package com.vegetablechicken.plantrecognition.controller;


import com.vegetablechicken.plantrecognition.request.ThoughtRequest;
import com.vegetablechicken.plantrecognition.request.UserRequest;
import com.vegetablechicken.plantrecognition.service.ThoughtService;
import com.vegetablechicken.plantrecognition.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api("想法")
@RestController
@RequestMapping("/thought")
public class ThoughtController {
    @Autowired
    private ThoughtService thoughtService;

    @PostMapping("/insertThought")
    @ApiOperation(value = "发布想法", notes = "发布新的想法", tags = "Thought",httpMethod = "POST")
    public String login(@RequestBody ThoughtRequest thoughtRequest){
        log.info("insertThought:{}",thoughtRequest);
        return thoughtService.insertThought(thoughtRequest.getUserid(),thoughtRequest.getContent());
    }


}
