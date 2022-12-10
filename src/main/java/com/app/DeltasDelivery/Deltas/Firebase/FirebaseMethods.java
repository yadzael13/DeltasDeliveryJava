package com.app.DeltasDelivery.Deltas.Firebase;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


import com.app.DeltasDelivery.Deltas.Tools.Loggers;


import com.google.cloud.firestore.DocumentReference;

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
            Loggers.errorLog("\n Error en firebase- getComercio()",e.toString());
            return null;
        }
        
    }
    
    /** Obtener producto nivel categoria por id
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
            Loggers.errorLog("Error en firebase- getCategory()",e.toString());
            return null;
        }
       
    }

    static HashMap<String, Object> getPromotion_product(String idRest, String idProd){
                HashMap<String, Object> ret = new HashMap<>();
                try {
                    HashMap<String, Object> aux = new HashMap<>();
                    DocumentReference ref = getProduct_principal(idRest, idProd);
                    aux = (HashMap<String, Object>) ref.get().get().getData();
                    Object prom = (Object) aux.get("promocion_Producto");
                    Boolean promStatus = (Boolean) aux.get("status_promocion");
                    ret.put("promocion_Producto", prom);
                    ret.put("status_promocion", promStatus);

                } catch (Exception e) {
                    Loggers.errorLog("Firebase Methods -- getPromotion_product", e.getMessage());
                }
                return ret;
    }

 //-------------------------------------- METODOS PARA VERIFICAR SI EXISTE -----------------------------------//
    /** Verificar si existe en fb o no el comercio
     * @param id -- Id de restaurante
     * @return True si existe, False si no
     * @throws InterruptedException
     * @throws ExecutionException
     */
    static Boolean exist_com(String id) throws InterruptedException, ExecutionException{
        try {
            DocumentReference aux = getRestaurante(id);
        if(!(aux.get().get().getData() == null)){
            return true;
        }
        } catch (Exception e) {
            Loggers.errorLog("Validar existencia de comercio", e.getMessage());
        }
       return false;
    }

     /** Verificar si existe en fb o no la categoria
     * @param id -- Id de restaurante, id cat
     * @return True si existe, False si no
     * @throws InterruptedException
     * @throws ExecutionException
     */
    static Boolean exist_cat(String idRest, String idCat) throws InterruptedException, ExecutionException{
        try {
            DocumentReference aux = getCategory(idRest, idCat);
        if(!(aux.get().get().getData() == null)){
            return true;
        }
        } catch (Exception e) {
            Loggers.errorLog("Validar existencia de comercio", e.getMessage());
        }
       return false;
    }

    /** Verificar si existe en fb o no el producto  con categoria
     * @param id -- Id de restaurante, id cat, id prod
     * @return True si existe, False si no
     * @throws InterruptedException
     * @throws ExecutionException
     */
    static Boolean exist_prod(String idRest, String idCat, String idProd) throws InterruptedException, ExecutionException{
        try {
            DocumentReference aux = getProduct(idRest,idCat,idProd);
        if(!(aux.get().get().getData() == null)){
            return true;
        }
        } catch (Exception e) {
            Loggers.errorLog("Validar existencia de comercio", e.getMessage());
        }
       return false;
    }

    //++------------------------------------------------- Producto a nivel principal
        /** Obtener producto nivel principal por id
     * @param idRestaurante
     * @param category
     * @param idProduct
     */
    static DocumentReference getProduct_principal(String idRestaurante, String product ){
        try{
            var ret = firebase.getFirestore()
                                .collection(col_name)
                                .document(idRestaurante)
                                .collection("products")
                                .document(product);
                               
            return ret;
        }catch(Exception e){
            Loggers.errorLog("Error en firebase- getProduct_rest() -[Nivel Principal]-",e.toString());
            return null;
        }
       
    }

    /** Verificar si existe en fb o no el producto  con categoria
     * @param id -- Id de restaurante, id cat, id prod
     * @return True si existe, False si no
     * @throws InterruptedException
     * @throws ExecutionException
     */
    static Boolean exist_prod_principal(String idRest, String idProd) throws InterruptedException, ExecutionException{
        try {
            DocumentReference aux = getProduct_principal(idRest, idProd);
        if(!(aux.get().get().getData() == null)){
            return true;
        }
        } catch (Exception e) {
            Loggers.errorLog("Validar existencia de comercio", e.getMessage());
        }
       return false;
    }


    
    //-------------------------------------- METODOS PARA CREAR  ---------------------------------------//
    
    /** Crear Restaurante en FireStore
     * @param idRestaurante
     * @param body
     */
     default void create_restaurante(String idRestaurante, Map<String, Object> body){
        try{
            var reference = getRestaurante(idRestaurante);
            reference.set(body);
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

       /** Crear o actualizarProducto 
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
            reference.set(body, SetOptions.merge());
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

    //-------------------------------------- METODOS PARA ELIMINAR  ---------------------------------------//

    /**Eliminar restaurante
     * @param rest_name
     */
    static void delete_restaurant(String rest_name){
        try {
            var reference = getRestaurante(rest_name);
            
            reference.delete();
            Loggers.infoLog("Delete Restaurant - Firebase Methods", "Se ha eliminado el restaurante: "+rest_name);
            
            
        } catch (Exception e) {
            Loggers.errorLog("Firebase Metohds - Delete Restaruant", e.toString());
        }
    }


    
    /** Eliminar prodicto a nivel categoria
     * @param rest
     * @param cat
     * @param prod_name
     */
    static void delete_product(String rest, String cat, String prod_name){
        try {
            var reference = getProduct(rest, cat, prod_name);
            
            reference.delete();
            Loggers.infoLog("Delete Product - Firebase Methods", "Se ha eliminado el Producto: "+prod_name+"\n -De la categoria: "+cat+ "\n -Del Restaurante "+rest);
            
            
        } catch (Exception e) {
            Loggers.errorLog("Firebase Metohds - Delete Product", e.toString());
        }
    }

   
    /**Eliminar producto a nivel principal
     * @param rest
     * @param prod_name
     */
    static void delete_product_principal(String rest, String prod_name){
        try {
            var reference = getProduct_principal(rest, prod_name);
            
            reference.delete();
            Loggers.infoLog("Delete Product_pricipal - Firebase Methods", "Se ha eliminado el Producto: "+prod_name+" de la categoria: "+"\n -Del Restaurante "+rest);
            
            
        } catch (Exception e) {
            Loggers.errorLog("Firebase Metohds - Delete Product_Principal", e.toString());
        }
    }


    /**Eliminar categoría
     * @param idRest
     * @param idCat
     */
    static void delete_category(String idRest, String idCat){
        try {
            var reference = getCategory(idRest, idCat);
            
            reference.delete();
            Loggers.infoLog("Delete category - Firebase Methods", "Se ha eliminado la categoria: "+idCat+"\n -Del comercio: "+ idRest);
            
            
        } catch (Exception e) {
            Loggers.errorLog("Firebase Metohds - Delete category", e.toString());
        }
    }


    /**Delete promotion -- Nivel producto
     * @param idRest
     * @param idProd
     */
    static void delete_promotion(String idRest, String idProd){
        try {
            HashMap<String, Object> aux = new HashMap<>();
            List<Object> l = new ArrayList<>();
            aux.put("promocion_Producto", l);
            aux.put("status_promocion", false);
            var p = getProduct_principal(idRest, idProd);
            p.set(aux,SetOptions.merge());
            Loggers.infoLog("Firebase Methods - delete promotion", "Se ha eliminado la promocion del producto: "+idProd+"\n--Del Restaurante: "+idRest);
        } catch (Exception e) {
            Loggers.errorLog("Firebase Methods -- delete_promotion[nivel principal]", e.toString());
        }
    }
    //------------------------------------------------------------------------------------------------------


    
}