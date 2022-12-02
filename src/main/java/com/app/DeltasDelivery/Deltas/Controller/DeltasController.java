package com.app.DeltasDelivery.Deltas.Controller;

//Librerias básicas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
//Entities y logica
import com.app.DeltasDelivery.Deltas.Logic.*;
import com.google.api.gax.rpc.StatusCode.Code;

// Json


import lombok.var;


// Indicamos que nuestra clase va ser tipo controlador
@RestController
@RequestMapping("/v1")

public class DeltasController {

    @Autowired
    ComercioLogic comercioLogic;

    @Autowired
    ProductsLogic productsLogic;

    @Autowired
    CategoryLogic categoryLogic;

    @Autowired
    PromotionLogic promotionLogic;
    


    /** CREAR O ACTUALIZAR RESTAURANTE
     * @param body -- cuerpo de información
     * @param action -- "create" ó "update"
     * @return ResponseEntity
     */
    @PostMapping("create_update_restaurant")
    public ResponseEntity <?> CreateUpdateRestaurant(
        @RequestBody HashMap<String, Object> body,
        @RequestHeader("action") String action ){
            HashMap<String, String> resp = new HashMap<>();

            String[] obligatorios= {"geoPoint","name"};
            for(String ob : obligatorios){
                if(!body.containsKey(ob)){
                    resp.put("Bad - Body", "El cuerpo mandando no contiene \'"+ob+"\'");
                    return ResponseEntity.status(401).body(resp);
                }
            }
            if(action.equals("create")){
                // var aux = comercioLogic.comercioCreate(body);
                var aux = ComercioLogic.metodos_es(body);
                String code = aux.getCode();
                return ResponseEntity.status(Integer.parseInt(code)).body(aux);
            } else if(action.equals("update")){

                comercioLogic.comercioUpdate(body);
                return ResponseEntity.status(200).body(resp);
            }else{
                resp.put("Bad - Header", "El parametro \'action\' en los Headers debe ser \'create\' ó \'update\'");
                return ResponseEntity.status(401).body(resp);
            }
            
            
        }

    /** ELIMINAR RESTAURANTE
     * @param body -- info para llegar al rest
     * @return ResponseEntity
     */
    @DeleteMapping("delete_restaurant")
    public ResponseEntity <?> DeleteRestaurant(
        @RequestBody HashMap<String, Object> body
         ){
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("Hello", "Function delete_restaurante");
            return ResponseEntity.status(200).body(resp);
        }

    /** CREAR O ACTUALIZAR PRODUCTO
     * @param body -- cuerpo de información
     * @param action -- "create" ó "update"
     * @return ResponseEntity
     */
    @PostMapping("create_update_product")
    public ResponseEntity <?> CreateUpdateProduct(
        @RequestBody HashMap<String, Object> body,
        @RequestHeader("action") String action ){
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("Hello", "Function create_update_product");
            return ResponseEntity.status(200).body(resp);
        }

    /** ELIMINAR PRODUCTO
     * @param body -- info para llegar al producto
     * @return ResponseEntity
     */
    @DeleteMapping("delete_product")
    public ResponseEntity <?> DeleteProduct(
        @RequestBody HashMap<String, Object> body
         ){
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("Hello", "Function delete_product");
            return ResponseEntity.status(200).body(resp);
        }

    /** CREAR O ACTUALIZAR CATEGORÍA
     * @param body -- cuerpo de información
     * @param action -- "create" ó "update"
     * @return ResponseEntity
     */
    @PostMapping("create_update_category")
    public ResponseEntity <?> CreateUpdateCategory(
        @RequestBody HashMap<String, Object> body,
        @RequestHeader("action") String action ){
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("Hello", "Function create_update_category");
            return ResponseEntity.status(200).body(resp);
        }

     /** ELIMINAR CATEGORÍA
     * @param body -- info para llegar a la categoría
     * @return ResponseEntity
     */
    @DeleteMapping("delete_category")
    public ResponseEntity <?> DeleteCategory(
        @RequestBody HashMap<String, Object> body
         ){
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("Hello", "Function delete_category");
            return ResponseEntity.status(200).body(resp);
        }

    /** CREAR O ACTUALIZAR PROMOCIÓN
     * @param body -- cuerpo de información
     * @param action -- "create" ó "update"
     * @return ResponseEntity
     */
    @PostMapping("create_update_promotion")
    public ResponseEntity <?> CreateUpdatePromotion(
        @RequestBody HashMap<String, Object> body,
        @RequestHeader("action") String action ){
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("Hello", "Function create_update_promotion");
            return ResponseEntity.status(200).body(resp);
        }


    /** ELIMINAR PROMOCIÓN
     * @param body -- info para llegar a la promo
     * @return ResponseEntity
     */
    @DeleteMapping("delete_promotion")
    public ResponseEntity <?> DeletePromotion(
        @RequestBody HashMap<String, Object> body
         ){
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("Hello", "Function delete_category");
            return ResponseEntity.status(200).body(resp);
        }
    }



