package com.app.DeltasDelivery.Deltas.Logic;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Firebase.FirebaseMethods;
import com.app.DeltasDelivery.Deltas.Tools.HashConverter;
import com.app.DeltasDelivery.Deltas.Tools.Loggers;



@Service
public class ProductsLogic {
   
    public static ResponseGeneral productCreate(HashMap<String, Object> body){
        ResponseGeneral resp = new ResponseGeneral();
        return resp;
    }

    public static ResponseGeneral productUpdate(HashMap<String, Object> body){
        ResponseGeneral resp = new ResponseGeneral();
        return resp;
    }

    public static ResponseGeneral productDelete(HashMap<String, Object> body){
        ResponseGeneral resp = new ResponseGeneral();
        return resp;
    }
}
