package com.vegetablechicken.plantrecognition.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlantSearchResponse {
    private String name;
    private long pid;
}
