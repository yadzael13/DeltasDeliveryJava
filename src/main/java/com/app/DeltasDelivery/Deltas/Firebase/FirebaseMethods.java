package com.app.DeltasDelivery.Deltas.Firebase;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.app.DeltasDelivery.Deltas.Entities.Loggers;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

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
     
    */

    
    FirebaseConection firebase = new FirebaseConection();

    //Nombre de la colección
    String col_name = "comercios_dev";

    
    //-------------------------------------- METODOS PARA OBTENER -----------------------------------//
    /** Obtener Comercio por id ------------*
     * @param idRestaurante
     */
    static DocumentReference getRestaurante(String idRestaurante) {
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
     * @param category
     * @param idProduct
     */
    static DocumentReference getProduct(String idRestaurante, String category, String idProduct ){
        try{
            var ret = firebase.getFirestore()
                                .collection(col_name)
                                .document(idRestaurante)
                                .collection("categories")
                                .document(category)
                                .collection("products")
                                .document(idProduct);
            return ret;
        }catch(Exception e){
            Loggers.errorLogger("Error en firebase- getProduct()",e.toString());
            return null;
        }
       
    }


       /** Obtener Categoria
     * @param idRestaurante
     * @param idPromo
     */
    static DocumentReference getCategory(String idRestaurante, String idCat){
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


    
    //-------------------------------------- METODOS PARA CREAR / ACTUALIZAR -----------------------------------//
    
    /** Crear o Actualizar Restaurante con método set()
     * @param idRestaurante
     * @param body
     * @return true si no ha habido error / false si cae en catch
     */
    static Boolean create_updateRestaurante(String idRestaurante, Map<String, Object> body){
        Boolean status;
        try{
            var reference = getRestaurante(idRestaurante);
            reference.set(body);
            Loggers.infoLogger("FirebaseMethods Succesfull", "Restarante creado --- "+idRestaurante);
            status = true;
            return status;
        } catch(Exception e){
            Loggers.errorLogger("createRestaurante() -- FirebaseMethods", e.toString());
            status = false;
            return status;
        }
        
    }

      /** Crear o Actualizar Categoria con método set()
     * @param idRestaurante
     * @param category
     * @param body
     * @return true si no ha habido error / false si cae en catch
     */
    static Boolean create_updateCategory(String idRestaurante, String category, Map<String, Object> body){
        Boolean status;
        try{
            var reference = getCategory(idRestaurante, category);
            reference.set(body);
            Loggers.infoLogger("FirebaseMethods Succesfull", "Categoria creada --- "+"comercio: "+idRestaurante+"\ncat: "+category);
            status = true;
            return status;
        } catch(Exception e){
            Loggers.errorLogger("createRestarante() -- FirebaseMethods", e.toString());
            status = false;
            return status;
        }
        
    }


       /** Crear o Actualizar Producto con método set()
     * @param idRestaurante
     * @param body
     * @return true si no ha habido error / false si cae en catch
     */
    static Boolean create_updateProduct(String idRestaurante, String category, String idProduct, Map<String, Object> body){
        Boolean status;
        try{
            var reference = getProduct(idRestaurante, category, idProduct);
            reference.set(body);
            Loggers.infoLogger("FirebaseMethods Succesfull", 
            "Producto creado--- "+"comercio: "+idRestaurante+"\ncat: "+category+"\nproduct: "+idProduct);
            status = true;
            return status;
        } catch(Exception e){
            Loggers.errorLogger("createRestarante() -- FirebaseMethods", e.toString());
            status = false;
            return status;
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