package com.app.DeltasDelivery.Deltas.Logic;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Firebase.FirebaseMethods;

@Service
public class ComercioLogic {

    public static ResponseGeneral comercioCreate(HashMap<String, Object>body){
        ResponseGeneral resp = new ResponseGeneral();
        String name = (String) body.get("name");
        return resp;
    }

    public static ResponseGeneral comercioUpdate(HashMap<String, Object>body){
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
