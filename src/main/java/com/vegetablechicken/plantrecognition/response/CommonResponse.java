package com.vegetablechicken.plantrecognition.response;

import com.alibaba.fastjson.JSONObject;

public class CommonResponse {
    public static JSONObject returnData(int code, String msg, Object data) {
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
}
