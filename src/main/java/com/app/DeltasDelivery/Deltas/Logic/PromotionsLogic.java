package com.app.DeltasDelivery.Deltas.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;


//Aun no esta completa, solo se creo el archivo, necesito validar varias cosas
public class PromotionsLogic {
    public static ResponseGeneral promotion_delete(String idRest, String idProd){
        HashMap<String, Object> h = new HashMap<>();
        List<?> l = new ArrayList<>();
        h.put("promocion_Producto", l);
        h.put("status_promocion", false);
        
        ResponseGeneral resp = new ResponseGeneral();
        return resp;
    }
}
