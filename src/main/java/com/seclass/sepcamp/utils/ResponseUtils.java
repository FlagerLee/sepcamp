package com.seclass.sepcamp.utils;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

public class ResponseUtils {
    public  static String MapToString(LinkedHashMap<String,String> resultMap){
        Gson gson = new Gson();
        String jsonStr = gson.toJson(resultMap);
        return jsonStr;
    }
}
