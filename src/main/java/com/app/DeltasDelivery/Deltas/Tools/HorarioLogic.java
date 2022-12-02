package com.app.DeltasDelivery.Deltas.Tools;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.cloud.firestore.GeoPoint;

import lombok.var;

public class HorarioLogic {

    public static HashMap<String, Object> metodos_estaticos(HashMap<String, Object> body){
        try {
            HashMap<String, Object> ret = new HashMap<>();
            String name = (String) body.get("name");

            //Horario
            HashMap sch = (HashMap) body.get("openingHours");
            List<HashMap<String, Object>> schedule = (List<HashMap<String, Object>>) sch.get("weekDayOpeningList");
            schedule = horario(schedule);

            //GeoPoint
            HashMap<String, Double> geo = (HashMap<String, Double>) body.get("geoPoint");
            GeoPoint geoPoint = new GeoPoint(geo.get("latitude"), geo.get("longitude"));

             //Obtener nombre desde vendor
             List listVendor = (List) body.get("warehousesDetail");
             HashMap auxVendor = (HashMap) listVendor.get(0);
             auxVendor = (HashMap) auxVendor.get("vendor");
             String nameVendor = (String) auxVendor.get("name");

            

            ret.put("name", nameVendor);
            ret.put("geoPoint", geoPoint);
            ret.put("schedule", schedule);
            return ret;
        } catch (Exception e) {
            Loggers.infoLogger("Metodos estaticos -Tools", e.toString());
            return null;
        }

    }
    
    public static List<HashMap<String, Object>>horario(List<HashMap<String, Object>> schedule2){
        try{
            List<HashMap<String, Object>> Schedule = new ArrayList<>();
            for(HashMap<String, Object> el : schedule2){
                var aux = outp(el);
                Schedule.add(aux);
            }
            return Schedule;
        } catch(Exception e){
            Loggers.errorLogger("HorarioLogic - Tools", e.toString());
            return null;
        }
        
        
    }

    public static HashMap<String, Object> outp(HashMap<String, Object> input){
        HashMap<String, Object> body = new HashMap<>();
        
        HashMap<String, Object> openingTime_hash = (HashMap<String, Object>) input.get("openingTime");
        String openingTime = (String) openingTime_hash.get("formattedHour");
        
        HashMap<String, Object> closingTime_hash = (HashMap<String, Object>) input.get("closingTime");
        String closingTime = (String) closingTime_hash.get("formattedHour");

        String day = (String) input.get("week_day");
        day = day_funct(day);

        Boolean closed = (Boolean) input.get("closed");

        if(closed){
            body.put("day", day);
            body.put("closed", closed);
            return body;
        }

        body.put("openingTime", openingTime);
        body.put("closingTime", closingTime);
        body.put("day", day);
        body.put("closed", closed);
        return body;
    }

    public static String day_funct(String given_day){
        String ret = "";
        switch (given_day) {
            case "lun.":
                ret = "Lunes";
                break;

            case "mar.":
                ret = "Martes";
                break;

            case "mie.":
                ret = "Miércoles";
                break;

            case "mié.":
                ret = "Miércoles";
                break;

            case "jue.":
                ret = "Jueves";
                break;

            case "vie.":
                ret = "Viernes";
                break;
            case "sab.":
                ret = "Sábado";
                break;
            case "sáb.":
                ret = "Sábado";
                break;         
            default:
                break;
        }
        return ret;
    }
}
