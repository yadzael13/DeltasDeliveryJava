package com.app.DeltasDelivery.Deltas.Tools;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentReference;

public class HashConverter {
    /** Convierte un objeto DocumentReference a HashMap
     * @param doc
     * @return Hashmap
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static HashMap<String, Object> hashConverter(DocumentReference doc) throws InterruptedException, ExecutionException{
        try{
            HashMap<String, Object> ret = (HashMap<String, Object>) doc.get().get().getData();
            return ret;
        } catch(Exception e){
            Loggers.errorLogger("HashConverter in Tools", e.toString());
            return null;
        }
    }
    
}
