package com.vegetablechicken.plantrecognition.request;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CommentRequest {
    private long tid;
    private String email;
    private String content;
}
