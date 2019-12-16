package com.vegetablechicken.plantrecognition.controller;


import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Thought;
import com.vegetablechicken.plantrecognition.request.ThoughtRequest;
import com.vegetablechicken.plantrecognition.service.ThoughtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        return thoughtService.insertThought(thoughtRequest.getEmail(),thoughtRequest.getContent(),thoughtRequest.getPic());
    }

    @GetMapping("/getThoughts")
    @ApiOperation(value = "得到想法", notes = "得到一个用户的所有想法", tags = "Thought",httpMethod = "GET")
    public List<Thought> getThoughts(@RequestParam String email){

    return thoughtService.getThought(email);
    }

    @GetMapping("/getSomeThoughts")
    @ApiOperation(value = "得到所有用户的想法", notes = "得到所有用户的num个想法", tags = "Thought",httpMethod = "GET")
    public List<Thought> getSomeThoughts(@RequestParam int num){

        return thoughtService.getSomeThoughts(num);
    }

    @GetMapping("/deleteThought")
    @ApiOperation(value = "删除想法", notes = "根据tid删除想法", tags = "Thought",httpMethod = "GET")
    public String deleteThought(@RequestParam String email,@RequestParam long tid){

        return thoughtService.deleteThought(email,tid);
    }

}
