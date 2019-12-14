package com.vegetablechicken.plantrecognition.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserInfoRequest {
    private String email;
    private String name;

}
