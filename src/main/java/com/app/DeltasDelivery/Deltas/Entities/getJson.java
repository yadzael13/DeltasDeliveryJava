package com.app.DeltasDelivery.Deltas.Entities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;


public class getJson {
 public static JSONObject getJsonObject(){
    String source = "JSONS/jsonprueba.json";
    InputStream is = getJson.class.getResourceAsStream(source);

    JSONTokener tokener = new JSONTokener(is);
    JSONObject object = new JSONObject(tokener);
    System.out.println("\n" + object);
    
    JSONArray arr_json = new JSONArray();
    arr_json = object.getJSONArray("data");
    System.out.println(arr_json.getJSONObject(0).put("bill1","111"));
    System.out.println(arr_json.getJSONObject(0).get("bill1"));
    

    System.out.println("\n" +object);


    
    return object;
 }
}
