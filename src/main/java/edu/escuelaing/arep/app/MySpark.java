package edu.escuelaing.arep.app;

import java.util.HashMap;
import java.util.Map;

public class MySpark {
    private static Map<String, String> endpoints = new HashMap<>();

    public static String get(String key){
        if(endpoints.containsKey(key)){
            return endpoints.get(key);
        }
        return null;
    }

    public static void post(String key, String value){
        endpoints.put(key,value);
    }
}
