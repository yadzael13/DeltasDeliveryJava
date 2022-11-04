package com.app.DeltasDelivery.Deltas.Logic;

import com.app.DeltasDelivery.Deltas.Entities.ErrorLogger;
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.getJson;



import java.util.HashMap;


import org.springframework.stereotype.Service;


@Service
public class jsonLogic {
    public static ResponseGeneral getJson(){
        ResponseGeneral response = new ResponseGeneral();
        try{
            response.setCode("200");
            response.setResult("Correct");
            response.setResultDescription("Json Obtenido correctamente");
            HashMap<String, Object> jsonHash = getJson.getJsonObject("products");
            response.setBody(jsonHash);
        } catch (Exception e){
            response.setCode("400");
            response.setResult("Error");
            response.setResultDescription("Json no encontrado");
            ErrorLogger.errorMessage("getJson into Logic \n  "+e.toString());
        }
        return response;
    }
}
