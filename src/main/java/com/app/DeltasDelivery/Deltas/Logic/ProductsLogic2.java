package com.app.DeltasDelivery.Deltas.Logic;

import java.util.HashMap;

import com.app.DeltasDelivery.Deltas.Entities.Loggers;
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.getJson;
import com.app.DeltasDelivery.Deltas.Firebase.FirebaseMethods;
import com.google.cloud.firestore.DocumentReference;



public class ProductsLogic2 {
    public static ResponseGeneral ProductLogic(HashMap<String, Object> hash_1){
        ResponseGeneral response = new ResponseGeneral();
        try{
            String source = "producto";
            response.setCode("400");
            response.setResult("Producto Creado/Actualizado");
            response.setResultDescription("Operación Correcta");
            String[] obligatorios= {"id"};
            HashMap<String, Object> productPlantilla = getJson.getJsonObject(source);
            DocumentReference objectFB = FirebaseMethods.getComercio("D1_1");
            HashMap<String, Object> object_hash = (HashMap<String, Object>) objectFB.get().get().getData();
            System.out.println(object_hash);

            System.out.println(objectFB);
            response.setBody(product);
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
