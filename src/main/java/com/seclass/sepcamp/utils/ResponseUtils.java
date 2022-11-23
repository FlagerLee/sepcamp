package com.seclass.sepcamp.utils;

import com.google.gson.Gson;
import com.seclass.sepcamp.models.Response;

import java.util.LinkedHashMap;

public class ResponseUtils {
    public  static String MapToString(LinkedHashMap<String,String> resultMap){
        Gson gson = new Gson();
        String jsonStr = gson.toJson(resultMap);
        return jsonStr;
    }
    public  static Response ResponseMaker(boolean IsSuccess, String SuccessString, String FalseString){
        if(IsSuccess){
            return new Response(SuccessString,true);
        }else{
            return new Response(FalseString,false);
        }
    }
}
