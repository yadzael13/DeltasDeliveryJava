package com.app.DeltasDelivery.Deltas.Logic;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.getJson;
import com.app.DeltasDelivery.Deltas.Firebase.FirebaseMethods;
import com.app.DeltasDelivery.Deltas.Tools.HashConverter;
import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.google.cloud.firestore.DocumentReference;


@Service
public class ProductsLogic {
    public static ResponseGeneral ProductLogic(HashMap<String, Object> productBody){
        ResponseGeneral response = new ResponseGeneral();
        try{
            String restaurante = productBody.get("id").toString();
            FirebaseMethods.create_updateRestaurante(restaurante, productBody);
            
            return response;
        } catch (Exception e){
            Loggers.errorLogger("Error en Product Logic en -- Logic ",e.toString());
            response.setCode("400");
            response.setResult("Producto no Creado/Actualizado");
            response.setResultDescription("Error");
            return response;
        }
    }
}
