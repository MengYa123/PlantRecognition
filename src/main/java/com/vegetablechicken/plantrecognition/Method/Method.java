package com.vegetablechicken.plantrecognition.Method;

import java.util.UUID;

public class Method {

    public static String getRandomUUid(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str;
    }
}
