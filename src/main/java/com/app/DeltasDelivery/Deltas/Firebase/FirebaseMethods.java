package com.app.DeltasDelivery.Deltas.Firebase;

import java.util.HashMap;
import java.util.Map;


import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.google.cloud.firestore.DocumentReference;

import com.google.cloud.firestore.SetOptions;

import lombok.var;
import org.json.JSONObject;

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
            Loggers.errorLog("\n Error en firebase- getComercio()",e.toString());
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
            Loggers.errorLog("Error en firebase- getProduct()",e.toString());
            return null;
        }
       
    }


       /** Obtener Categoria
     * @param idRestaurante
     * @param
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
            Loggers.errorLog("Error en firebase- getPromotions()",e.toString());
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

            System.out.println("Ya entro el JSON");
            System.out.println(body);

            DocumentReference reference = getRestaurante(idRestaurante);
            reference.set(new JSONObject(body).toMap());
            Loggers.infoLog("Restaurante Creado--- "+idRestaurante, "Succesfull");
            
        } catch(Exception e){
            Loggers.errorLog("create_restaurante() -- FirebaseMethods", e.toString());
            
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
            Loggers.infoLog("Categoria Creada / Actualizada --- "+"comercio: "+idRestaurante+"\ncat: "+category,"Succesfull");
    
        } catch(Exception e){
            Loggers.errorLog("create_updateCategory() -- FirebaseMethods", e.toString());
          
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
            Loggers.infoLog( "Producto Creado / Actualizado --- "+"comercio: "+idRestaurante+"\ncat: "+category+"\nproduct: "+idProduct,
                            "Succesfull");
        
        } catch(Exception e){
            Loggers.errorLog("create_updateProduct() -- FirebaseMethods", e.toString());
    
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
            reference.update(new JSONObject(body).toMap());
            Loggers.infoLog("Restaurante Actualizado--- "+idRestaurante, "Succesfull");
        } catch(Exception e){
            Loggers.errorLog("update_restaurante() -- FirebaseMethods", e.toString());
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
            Loggers.infoLog("Producto Actualizado--- "+idRestaurante, "Succesfull");
        } catch(Exception e){
            Loggers.errorLog("update_product() -- FirebaseMethods", e.toString());
        } 
    }

    //----------------------------------------------------------------------------

    default DocumentReference getComercio(String idRestaurante) {
        assert false;
        return firebase.getFirestore()
                .collection(col_name)
                .document(idRestaurante);

    }


}