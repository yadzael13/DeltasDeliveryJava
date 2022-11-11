package com.app.DeltasDelivery.Deltas.Firebase;

import com.app.DeltasDelivery.Deltas.Entities.Loggers;
import com.google.cloud.firestore.DocumentReference;

import lombok.var;

public interface FirebaseMethods {

    /*
     *Deltas Firebase Methods
     *------COLABS--------*
     Juan Carlos Vargas
     Brenda Medina
     Hiram Yadzael
    *---------------------*
            2022 
    ---------------------
     Todos los métodos de éste documento retornan un objeto tipo:
     -DocumentReference
     -CollectionReference
    */

    
    FirebaseConection firebase = new FirebaseConection();

    //Nombre de la colección
    String col_name = "comercios_dev";

    
    //-------------------------------------- METODOS GET -----------------------------------//
    /** Obtener Comercio por id ------------*
     * @param idRestaurante
     */
    static DocumentReference getComercio(String idRestaurante) {
        try{
            var ret = firebase.getFirestore()
            .collection(col_name)
            .document(idRestaurante);
            return ret;
        } catch (Exception e){
            Loggers.errorLogger("\n Error en firebase- getComercio()",e.toString());
            return null;
        }
        
    }
    
    /** Obtener producto por id
     * @param idRestaurante
     * @param idProduct
     */
    static DocumentReference getProduct(String idRestaurante, String idProduct ){
        try{
            var ret = firebase.getFirestore()
                                .collection(col_name)
                                .document(idRestaurante)
                                .collection("products")
                                .document(idProduct);
            return ret;
        }catch(Exception e){
            Loggers.errorLogger("Error en firebase- getProduct()",e.toString());
            return null;
        }
       
    }

    /** Obtener Promocion
     * @param idRestaurante
     * @param idPromo
     */
    static DocumentReference getPromotions(String idRestaurante, String idPromo ){
        try{
            var ret = firebase.getFirestore()
                                .collection(col_name)
                                .document(idRestaurante)
                                .collection("categories")
                                .document("PROMOTIONS")
                                .collection("products")
                                .document(idPromo);
            return ret;
        }catch(Exception e){
            Loggers.errorLogger("Error en firebase- getPromotions()",e.toString());
            return null;
        }
       
    }

       /** Obtener Categoria
     * @param idRestaurante
     * @param idPromo
     */
    static DocumentReference getCategory(String idRestaurante, String idCat ){
        try{
            var ret = firebase.getFirestore()
                                .collection(col_name)
                                .document(idRestaurante)
                                .collection("categories")
                                .document(idCat);
            return ret;
        }catch(Exception e){
            Loggers.errorLogger("Error en firebase- getPromotions()",e.toString());
            return null;
        }
       
    }



    //Métodos de Charly, Dispositivos, monitoreo, etc
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