package com.app.DeltasDelivery.Deltas.Tools;

import java.util.HashMap;

public class HashCombine {
    public static HashMap<String, Object> combine(HashMap<String, Object>existing, HashMap<String, Object>nuevo){
        try {
            HashMap<String, Object> ret = new HashMap<>();

            nuevo.forEach((k,v) -> 
                               existing.put(k, v)
                            );
            return existing;
        } catch (Exception e) {
            Loggers.errorLogger("Tools / HashCombine", e.toString());
            return null;
        }
     
    }
}
