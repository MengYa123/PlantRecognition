package com.vegetablechicken.plantrecognition.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ThoughtRequest {
    private String userid;
    private String content;


}
