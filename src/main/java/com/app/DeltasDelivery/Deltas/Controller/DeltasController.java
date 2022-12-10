package com.app.DeltasDelivery.Deltas.Controller;

//Librerias básicas
import com.app.DeltasDelivery.Deltas.Entities.Category.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Entities y logica
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Logic.ProductsLogic;
import com.app.DeltasDelivery.Deltas.Logic.RestaurantsLogic;
import com.app.DeltasDelivery.Deltas.Logic.CategoriesLogic;

//Herramientas
import lombok.var;
import java.util.HashMap;

import com.app.DeltasDelivery.Deltas.Tools.Loggers;


// Indicamos que nuestra clase va ser tipo controlador
@RestController
@RequestMapping("/v1")

public class DeltasController {

    @Autowired
    ProductsLogic productsLogic;

    @Autowired
    RestaurantsLogic restaurantsLogic;

    @Autowired
    CategoriesLogic categoriesLogic;


    @PostMapping("/create_restaurant")
    public ResponseEntity <?> createRestaurant(
            @RequestBody HashMap<String, Object> body,
            @RequestHeader("env") String env,
            @RequestHeader("ban") String ban
            // Mando a funcion body, env
    ){
        try {
            // ********* Tenemos que colocar loggs de los bodys que entran **********
            Loggers.infoLog("/create_restaurant","Request ="+body.toString());
            // ******** Lógica para obligatorios
            String[] obligatorios = {"geoPoint", "name", "openingHours"};
            for(String ob : obligatorios){
                if(!body.containsKey(ob)){
                     ResponseGeneral resp = new ResponseGeneral();
                     resp.setCode("400");
                     resp.setResult("No se ha creado el restaurante");
                     resp.setResultDescription("Es necesario agregar "+ob);
                     int code = Integer.parseInt(resp.getCode());
                     return ResponseEntity.status(HttpStatus.valueOf(code)).body(resp);
                }
            }
            var result = restaurantsLogic.restaurants(body,env,ban);

            // ------------ MEJORA obtenermos CODE en string y en el controlador se convierte en HttpStatus
            int code = Integer.parseInt(result.getCode());
            return ResponseEntity.status(HttpStatus.valueOf(code)).body(result);

        }catch (Exception e){
            // ********* Tenemos que colocar obtener error personalizado **********
            Loggers.errorLog("create_restaurant",e.toString());
            ResponseGeneral res = new ResponseGeneral();
            res.setCode("500");
            res.setResult("Error en la operación");
            res.setResultDescription("Favor de validar Servicio");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PostMapping("/create_category")
    public ResponseEntity<?> createCategory(
            @RequestBody Category body,
            @RequestHeader("env") String env,
            @RequestHeader("ban") String ban
    ){
        try {
            // ********* Tenemos que colocar loggs de los bodys que entran **********
            Loggers.infoLog("/create_category","Request ="+body.toString());

            //var result = categoriesLogic.categories(body,env,ban);

            // ------------ MEJORA obtenermos CODE en string y en el controlador se convierte en HttpStatus
           // int code = Integer.parseInt(result.getCode());
           // return ResponseEntity.status(HttpStatus.valueOf(code)).body(result);
            return null;

        }catch (Exception e){
            // ********* Tenemos que colocar obtener error personalizado **********
            Loggers.errorLog("create_restaurant",e.toString());
            ResponseGeneral res = new ResponseGeneral();
            res.setCode("500");
            res.setResult("Error en la operación");
            res.setResultDescription("Favor de validar Servicio");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

    }


    @PostMapping("/create_product")
    public ResponseEntity <?> createProduct(
            @RequestBody HashMap body,
            @RequestHeader("env") String env
            // Mando a funcion body, env
    ){

        var result = productsLogic.products(body,env);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PostMapping("/delete_restaurant")
    public ResponseEntity<?> deleteRestaurante(
        @RequestBody HashMap<String, String> body
            ){
                ResponseGeneral result = new ResponseGeneral();
                try {
                    String name = (String) body.get("name");
                     result = restaurantsLogic.restaurantDelete(name);
                    if(result.getCode().equals("400")){
                        return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
                    }
                    return ResponseEntity.status(HttpStatus.CREATED).body(result);
                    
                } catch (Exception e) {
                    result.setCode("400");
                    result.setResult("Operacion con error");
                    result.setResultDescription("Eliminacion de restaurante fallida");
                    return  ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
                }
                   
                    
                
               
            }

        @PostMapping("/delete_product")
        public ResponseEntity <?> deleteProduct(
                @RequestBody HashMap<String, String> body
                // Mando a funcion body, env
        ){
            
            ResponseGeneral resp = new ResponseGeneral();
            try {
                String[] obligatorios = {"id_Producto", "id_Categoria", "id_Comercio"};
                for(String ob : obligatorios){
                    if(!body.containsKey(ob)){
                     
                         resp.setCode("404");
                         resp.setResult("No se pudo consultar la petición");
                         resp.setResultDescription("Favor de validar "+ob+" del servicio");
                         int code = Integer.parseInt(resp.getCode());
                         return ResponseEntity.status(HttpStatus.valueOf(code)).body(resp);
                    }
                }
                String nameProd = body.get("id_Producto");
                String nameCat = body.get("id_Categoria");
                String nameCom = body.get("id_Comercio");
                resp = productsLogic.productDelete(nameCom, nameCat, nameProd);

                return ResponseEntity.status(HttpStatus.CREATED).body(resp);
            } catch (Exception e) {
                Loggers.errorLog("Paths, delete_product", e.getMessage());
                resp.setCode("400");
                resp.setResult("Operacion incorrecta");
                resp.setResultDescription("Hubo un error al eliminar el producto Favor de verificar datos");
                return ResponseEntity.status(400).body(resp);
            }
            }
            
            

            @PostMapping("/delete_category")
            public ResponseEntity <?> deleteCategory(
                    @RequestBody HashMap<String, String> body
                    // Mando a funcion body, env
            ){
                
                ResponseGeneral resp = new ResponseGeneral();
                try {
                    String[] obligatorios = {"id_commerce", "id_category"};
                    for(String ob : obligatorios){
                        if(!body.containsKey(ob)){
                         
                             resp.setCode("404");
                             resp.setResult("No se pudo consultar la petición");
                             resp.setResultDescription("Favor de validar "+ob+" del servicio");
                             int code = Integer.parseInt(resp.getCode());
                             return ResponseEntity.status(HttpStatus.valueOf(code)).body(resp);
                        }
                    }
                    String nameCat = body.get("id_category");
                    String nameCom = body.get("id_commerce");
                    resp = categoriesLogic.categoryDelete(nameCom, nameCat);
                    int code = Integer.parseInt(resp.getCode());
                    if(code == 404 || code == 205){
                        code = 400;
                    }
                    return ResponseEntity.status(HttpStatus.valueOf(code)).body(resp);
                } catch (Exception e) {
                    Loggers.errorLog("Paths, Delete Category", e.getMessage());
                    resp.setCode("400");
                    resp.setResult("Operacion incorrecta");
                    resp.setResultDescription("Hubo un error al eliminar el producto Favor de verificar datos");
                    return ResponseEntity.status(400).body(resp);
                }
        }
        @PostMapping("/delete_promotion")
        public ResponseEntity <?>  deletePromotions(
            @RequestBody HashMap<String, Object> body
             ){
                ResponseGeneral resp = new ResponseGeneral();
            try {
                //Logica promocion por producto con cat
                //Logica promoicon producto directo
                int code = Integer.parseInt(resp.getCode());

                if(code == 205){
                    code = 200;
                }
                return ResponseEntity.status(code).body(resp);

                
            } catch (Exception e) {
                Loggers.errorLog("Paths -- delete_promotion", e.toString());
                resp.setCode("500");
                resp.setResult("Ha ocurrido un error");
                resp.setResultDescription("Error al eliminar promocion, verifique datos");

                return ResponseEntity.status(HttpStatus.valueOf(400)).body(resp);
            }

        }

}
