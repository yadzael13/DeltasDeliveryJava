package com.app.DeltasDelivery.Deltas.Firebase;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.SetOptions;

import lombok.var;

public interface FirebaseMethods {

    /*
     *Deltas Firebase Methods
     *------COLABS--------*
     Brenda Medina Arrollo
     Hiram Yadzael Vargas Chalico
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


    
    //-------------------------------------- METODOS PARA CREAR  ---------------------------------------//
    
    /** Crear Restaurante en FireStore
     * @param idRestaurante
     * @param body
     */
    static void create_restaurante(String idRestaurante, Map<String, Object> body){
        try{
            var reference = getRestaurante(idRestaurante);
            reference.set(body);
            Loggers.infoLogger("Restaurante Creado--- "+idRestaurante, "Succesfull");
            
        } catch(Exception e){
            Loggers.errorLogger("create_restaurante() -- FirebaseMethods", e.toString());
            
        }
        
    }


      /** Crear o Actualizar Categoria con método set()
     * @param idRestaurante
     * @param category
     * @param body
     */
    static void create_updateCategory(String idRestaurante, String category, Map<String, Object> body){
        
        try{
            var reference = getCategory(idRestaurante, category);
            reference.set(body);
            Loggers.infoLogger("Categoria Creada / Actualizada --- "+"comercio: "+idRestaurante+"\ncat: "+category,"Succesfull");
    
        } catch(Exception e){
            Loggers.errorLogger("create_updateCategory() -- FirebaseMethods", e.toString());
          
        }
        
    }

       /** Crear Producto 
     * @param idRestaurante
     * @param body
     */
    static void create_updateProduct(String idRestaurante, String category, String idProduct, Map<String, Object> body){
        
        try{
            var reference = getProduct(idRestaurante, category, idProduct);
            reference.set(body);
            Loggers.infoLogger( "Producto Creado / Actualizado --- "+"comercio: "+idRestaurante+"\ncat: "+category+"\nproduct: "+idProduct,
                            "Succesfull");
        
        } catch(Exception e){
            Loggers.errorLogger("create_updateProduct() -- FirebaseMethods", e.toString());
    
        }
    }

    //-------------------------------------- METODOS PARA ACTUALIZAR  ---------------------------------------//

    /**Actualizar Restaurante 
     * @param idRestaurante
     * @param body
     */
    static void update_restaurante(String idRestaurante, Map<String, Object> body){    
        try{
            var reference = getRestaurante(idRestaurante);
            reference.set(body, SetOptions.merge());
            Loggers.infoLogger("Restaurante Actualizado--- "+idRestaurante, "Succesfull");
        } catch(Exception e){
            Loggers.errorLogger("update_restaurante() -- FirebaseMethods", e.toString());
        } 
    }

    /**Actualizar Producto
     * @param idRestaurante
     * @param body
     */
    static void update_product(String idRestaurante, String category, String idProduct, Map<String, Object> body){    
        try{
            var reference = getProduct(idRestaurante, category, idProduct);
            reference.set(body, SetOptions.merge());
            Loggers.infoLogger("Producto Actualizado--- "+idRestaurante, "Succesfull");
        } catch(Exception e){
            Loggers.errorLogger("update_product() -- FirebaseMethods", e.toString());
        } 
    }

    //----------------------------------------------------------------------------
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