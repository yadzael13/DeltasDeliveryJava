package com.app.DeltasDelivery.Deltas.Entities;

import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class getJson {

 /** Función que convierte un archivo .json a un objeto HashMap
 * @param el -- String para buscar el json
 * @return --  Archivo .json convertido a Hashmap
 */
public static HashMap<String, Object> getJsonObject(String el){
    String source = "JSONS/"+el+".json";
try{
    InputStream getLocalJsonFile = getJson.class.getResourceAsStream(source);
    HashMap<String,Object> jsonMap = new ObjectMapper().readValue(getLocalJsonFile, HashMap.class);
    return jsonMap;
} catch (IOException e){
    Loggers.errorLogger("getJsonObject function (To convert .json into Hashmap)",e.toString());
}
HashMap<String,Object> jsonMap = new HashMap<>();
return jsonMap;
 }
}
