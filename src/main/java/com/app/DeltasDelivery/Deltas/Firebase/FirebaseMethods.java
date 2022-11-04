package com.app.DeltasDelivery.Deltas.Firebase;

import com.app.DeltasDelivery.Deltas.Entities.ErrorLogger;
import com.google.cloud.firestore.DocumentReference;

import lombok.var;

public interface FirebaseMethods {

    FirebaseConection firebase = new FirebaseConection();
    String col_name = "comercios_dev";
    static DocumentReference getComercio(String idRestaurante) {
        try{
            assert false;
            var ret = firebase.getFirestore()
            .collection(col_name)
            .document(idRestaurante);
            return ret;
        } catch (Exception e){
            ErrorLogger.errorMessage("\n Error en firebase- getComercio()"+e.toString());
            return null;
        }
        
    }
    //Pendiente, Preguntar a Bren como llego a los productos para consultar directamente un producto con id
    static DocumentReference getProduct(String idRestaurante, String category, String idProduct ){
        var ret = firebase.getFirestore()
        //De qui apr abajo hay porquerías :P, no sé como es la estructura en la DB real
        .collection(col_name)
        .document(idRestaurante)
        .collection("categories")
        .document(category);
        return ret;
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