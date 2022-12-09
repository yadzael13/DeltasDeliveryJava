package com.app.DeltasDelivery.Deltas.Logic;

//Entities
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Firebase.FirebaseMethods;
import com.app.DeltasDelivery.Deltas.Tools.Loggers;

import org.springframework.stereotype.Service;

//Tools
import org.json.JSONObject;
import java.util.HashMap;

@Service
public class CategoriesLogic {


    public ResponseGeneral categoryDelete(String idRest, String idCat){
        ResponseGeneral resp = new ResponseGeneral();
        try {

            Boolean validCat = FirebaseMethods.exist_cat(idRest, idCat);
            Boolean validRest = FirebaseMethods.exist_com(idRest);
            if(validCat && validRest){
                FirebaseMethods.delete_category(idRest, idCat);
                resp.setCode("200");
                resp.setResult("Operacion exitosa");
                resp.setResultDescription("Se ha eliminado la categoria correctamente");
            }else if(!validRest){
                //HTTP-400
                resp.setCode("205");
                resp.setResult("Operacion con Error");
                resp.setResultDescription("El comercio no existe");
           } else if(!validCat){
                //HTTP-400
                resp.setCode("205");
                resp.setResult("Operacion con Error");
                resp.setResultDescription("La categoria no existe");
            
            } 
            
        } catch (Exception e) {
            Loggers.errorLog("CategoriesLogic - categoryDelete", e.getMessage());

            resp.setCode("400");
            resp.setResult("Operacion con Error");
            resp.setResultDescription("Error al eliminar categorias");
        }
        return resp;
    }
}
