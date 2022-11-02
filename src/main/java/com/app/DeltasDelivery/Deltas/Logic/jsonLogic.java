package com.app.DeltasDelivery.Deltas.Logic;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.getJson;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class jsonLogic {
    public static ResponseGeneral getJson(){
        
        ResponseGeneral response = new ResponseGeneral();
        response.setCode("200");
        response.setResult("Neeel");
        response.setResultDescription("function getJson() (y)");
        

    
        JSONObject ret = new JSONObject();
        ret = getJson.getJsonObject();
        response.setBody(ret);
        
        return response;
    }
}
