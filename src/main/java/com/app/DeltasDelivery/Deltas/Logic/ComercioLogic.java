package com.app.DeltasDelivery.Deltas.Logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.getJson;
import com.app.DeltasDelivery.Deltas.Firebase.FirebaseMethods;
import com.app.DeltasDelivery.Deltas.Tools.HashCombine;
import com.app.DeltasDelivery.Deltas.Tools.HorarioLogic;
import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.google.cloud.firestore.GeoPoint;

import lombok.var;

@Service
public class ComercioLogic {


    public static ResponseGeneral metodos_es(HashMap<String, Object> body){
        ResponseGeneral resp = new ResponseGeneral();
        try {
            HashMap<String, Object> method = HorarioLogic.metodos_estaticos(body);
            System.out.println("**-----\n\n"+method);
            resp.setCode("200");
            resp.setResult("Sucessfully");
            resp.setResultDescription("Info consumida correctamente");
            resp.setBody(method);
            return resp;
        } catch (Exception e) {
            Loggers.errorLogger("metodos_es - Logic", e.toString());
            resp.setCode("401");
            resp.setResult("Ha ocurrido un error");
            resp.setResultDescription("Error en metodos_es");
            return resp;
        }
    }





    /**
     * @param body
     * @return -- ResponseGeneral
     */
    public ResponseGeneral  comercioCreate(HashMap<String, Object>insertBody){
        ResponseGeneral resp = new ResponseGeneral();
        try {

            //Host para imagenes
            String host_imagenes_prod = "https://backoffice.c6exvb10-totalplay1-p1-public.model-t.cc.commerce.ondemand.com";


          

            //Adress
            HashMap address = (HashMap) insertBody.get("address");


            //NAME
            String name = (String) insertBody.get("name");

            //GeoPoint
            HashMap<String, Double> geo = (HashMap<String, Double>) insertBody.get("geoPoint");
            GeoPoint geoPoint = new GeoPoint(geo.get("latitude"), geo.get("longitude"));

            //Lógica de Horario
            
            HashMap sch = (HashMap) insertBody.get("openingHours");
            var schedule = sch.get("weekDayOpeningList");
            schedule = HorarioLogic.horario((List<HashMap<String, Object>>) schedule);
            
            //Falta mapear imagenes y añadir host de qa y prod
            HashMap mapIcon = (HashMap) insertBody.get("mapIcon");
            String mapIcon_url = (String) insertBody.get("url");

            List storeImages = (List) insertBody.get("storeImages");
            Map store_map = (Map) storeImages.get(0);
            String store_url = (String) store_map.get("url");
            Map imagenes = new HashMap<>();
            imagenes.put("mapIcon", mapIcon_url);
            
            //Obtener nombre desde vendor
            List listVendor = (List) insertBody.get("warehousesDetail");
            HashMap auxVendor = (HashMap) listVendor.get(0);
            auxVendor = (HashMap) auxVendor.get("vendor");
            String nameVendor = (String) auxVendor.get("name");
            
            String nameCommerce = (String) insertBody.get("displayName");

            String phone = (String) address.get("phone");
            address.remove("phone");

            String description = (String) insertBody.get("description");
            //   categoryFilter
            List categoryFilter = (List) auxVendor.get("categoriesFood");
            HashMap cat_id_aux = (HashMap) categoryFilter.get(1);
            String cat_id = (String) cat_id_aux.get("id");
            String cat_name = (String) cat_id_aux.get("name");
            
            cat_id_aux.clear();
            cat_id_aux.put("id", cat_id);
            cat_id_aux.put("name", cat_name);

            //Armado de plantilla 
            HashMap<String, Object> plantilla = new HashMap<>();
            plantilla.put("score", "0");
            plantilla.put("geoPoint", geoPoint);
            plantilla.put("address", address);
            plantilla.put("schedule", schedule);
            plantilla.put("imagesCommerce", imagenes);
            plantilla.put("name", nameVendor);
            plantilla.put("nameCommerce", nameCommerce);
            plantilla.put("phone", phone);
            plantilla.put("typeCommerce", "Restaurante");
            plantilla.put("description", description);
            plantilla.put("categoryFilter", cat_id_aux);
            plantilla.put("status", "ACTIVE");
            
            FirebaseMethods.create_restaurante(name, plantilla);

            // HashMap<String, Object> plantilla = new HashMap<>();
            // plantilla = getJson.getJsonObject("restaurante");

            // HashMap<String, Object> newJsonFb = new HashMap<>();
            // HashCombine obj = new HashCombine(plantilla, insertBody);
            // newJsonFb = obj.combine();
            resp.setCode("200");
            resp.setResult("Operación Correcta");
            resp.setResultDescription("Restaurante con id: "+name+" creado correctamente.");
            resp.setBody(plantilla);
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
