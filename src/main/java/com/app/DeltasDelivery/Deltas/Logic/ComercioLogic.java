package com.app.DeltasDelivery.Deltas.Logic;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.getJson;
import com.app.DeltasDelivery.Deltas.Tools.HashCombine;
import com.app.DeltasDelivery.Deltas.Tools.Loggers;

@Service
public class ComercioLogic {

    /**
     * @param body
     * @return -- ResponseGeneral
     */
    public ResponseGeneral comercioCreate(HashMap<String, Object>insertBody){
        ResponseGeneral resp = new ResponseGeneral();
        try {
        String name = (String) insertBody.get("name");
        HashMap<String, Object> plantilla = new HashMap<>();
        plantilla = getJson.getJsonObject("restaurante");

        HashMap<String, Object> newJsonFb = new HashMap<>();
        HashCombine obj = new HashCombine(plantilla, insertBody);
        newJsonFb = obj.combine();
        resp.setCode("200");
        resp.setResult("Operación Correcta");
        resp.setResultDescription("Restaurante con id: "+name+" creado correctamente.");
        resp.setBody(newJsonFb);

        return resp;
        } catch (Exception e) {
            Loggers.errorLogger("comercioCreate on ComercioLogic", e.toString());
            resp.setCode("500");
            resp.setResult("Ha ocurrido un error al crear el restaurante");
            resp.setResultDescription("ComercioLogic");
            return resp;
        } 
           
        
        
    }

    public ResponseGeneral comercioUpdate(HashMap<String, Object>body){
        ResponseGeneral resp = new ResponseGeneral();
        String name = (String) body.get("name");
        return resp;
    }

    public static ResponseGeneral comercioDelete(HashMap<String, Object>body){
        ResponseGeneral resp = new ResponseGeneral();
        String name = (String) body.get("name");
        return resp;
    }
}
