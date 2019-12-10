package com.vegetablechicken.plantrecognition.controller;


import com.vegetablechicken.plantrecognition.entity.Thought;
import com.vegetablechicken.plantrecognition.request.ThoughtRequest;
import com.vegetablechicken.plantrecognition.service.ThoughtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getThoughts")
    @ApiOperation(value = "得到想法", notes = "得到想法", tags = "Thought",httpMethod = "GET")
    public List<Thought> getThoughts(@RequestParam String userid){

    return thoughtService.getThought(userid);
    }

}
