package com.app.DeltasDelivery.Deltas.Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class getJson {

 /**
 * @param el -- String para buscar el json
 * @return --  Archivo .json convertido a hashmap
 */
public static HashMap<String, Object> getJsonObject(String el){
    String source = "JSONS/"+el+".json";
try{
    InputStream getLocalJsonFile = getJson.class.getResourceAsStream(source);
    HashMap<String,Object> jsonMap = new ObjectMapper().readValue(getLocalJsonFile, HashMap.class);
    System.out.println(jsonMap);
    return jsonMap;
} catch (IOException e){
    ErrorLogger.errorMessage("getJsonObject function \n  "+e.toString());
}
HashMap<String,Object> jsonMap = new HashMap<>();
return jsonMap;
 }
}
