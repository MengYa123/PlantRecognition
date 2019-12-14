package com.vegetablechicken.plantrecognition.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReducePlantsResponse {
    private long pid;
    private String name;
    private String pic;
    private String kind;
}
