package com.app.DeltasDelivery.Deltas.Firebase;

import com.app.DeltasDelivery.Deltas.Entities.ErrorLogger;
import com.google.cloud.firestore.DocumentReference;

import lombok.var;

public interface FirebaseMethods {

    FirebaseConection firebase = new FirebaseConection();

    static DocumentReference getComercio(String idRestaurante) {
        try{
            assert false;
            var ret = firebase.getFirestore()
            .collection("comercios_dev")
            .document(idRestaurante);
            return ret;
        } catch (Exception e){
            ErrorLogger.errorMessage("\n Error en firebase- getComercio()"+e.toString());
            return null;
        }
        
    }

    default DocumentReference getPOS(String pointsOfService){
        
        assert false;
        
        return firebase.getFirestore()
                        .collection("dispositivos")
                        .document(pointsOfService);
    }

    default DocumentReference insertversion(String idDispositivo){
        assert false;
        return firebase.getFirestore()
                        .collection("Historicodispositivos")
                        .document(idDispositivo);
    }

    default DocumentReference getInfo(String idDispositivo) {
        assert false;
        return firebase.getFirestore()
                        .collection("Historicodispositivos")
                        .document(idDispositivo);
        
    }

    default DocumentReference insertdevices(String pointOfService){
        assert false;
        return firebase.getFirestore()
                        .collection("NotificacionesFirebase")
                        .document(pointOfService);
    }

    default DocumentReference getNotificacionesFirebase(String PointOfService) {
        assert false;
        return firebase.getFirestore()
                        .collection("NotificacionesFirebase")
                        .document(PointOfService);
        
    }

    default DocumentReference OrderMonitoring(String idOrder){
        assert false;
        return firebase.getFirestore()
                        .collection("monitoreo")
                        .document(idOrder);
    }

    default DocumentReference dispositivos(String pointOfService){
        assert false;
        return firebase.getFirestore()
                        .collection("dispositivos")
                        .document(pointOfService);
    }
    
}