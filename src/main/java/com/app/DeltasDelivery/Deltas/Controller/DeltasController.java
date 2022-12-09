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


    @GetMapping("/create_restaurant")
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

    @GetMapping("/create_category")
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


    @GetMapping("/create_product")
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

           
    

}
