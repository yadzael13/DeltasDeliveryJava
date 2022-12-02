package com.app.DeltasDelivery.Deltas.Tools;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HashCombine {
    public HashMap<String, Object> existing;
    public HashMap<String, Object> nuevo;

    public  HashMap<String, Object> combine(){
        try {

            nuevo.forEach((k,v) -> 
                                combineLogic(k,v)
                            );
            return existing;
        } catch (Exception e) {
            Loggers.errorLogger("Tools / HashCombine", e.toString());
            return null;
        }
     
    }
    private void combineLogic(String k, Object v){
        if(existing.containsKey(k)){
            String type_existing = existing.get(k).getClass().getSimpleName(); 
            String type_nuevo = nuevo.get(k).getClass().getSimpleName();
            if(type_existing == type_nuevo){
                existing.put(k, v);
            }
            
        }else{
            Loggers.infoLogger("CombineLogic - Tools", k+" No existe como llave");
        }
    }
   

    
}
