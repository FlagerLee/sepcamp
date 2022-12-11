package com.seclass.sepcamp.utils;

import com.seclass.sepcamp.models.Response;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.LinkedHashMap;

public class ResponseUtils {
    public  static String MapToString(LinkedHashMap<String,String> resultMap){
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public  static Response ResponseMaker(boolean IsSuccess, String SuccessString, String FalseString){
        if(IsSuccess){
            return new Response(SuccessString,true);
        }else{
            return new Response(FalseString,false);
        }
    }
}
