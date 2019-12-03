package com.vegetablechicken.plantrecognition.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecognitionResultResponse {
    private String pic;
    private String name;
}
