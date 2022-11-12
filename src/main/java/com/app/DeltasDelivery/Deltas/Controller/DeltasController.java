package com.app.DeltasDelivery.Deltas.Controller;

//Librerias básicas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//Entities y logica
import com.app.DeltasDelivery.Deltas.Logic.*;


// Json


import lombok.var;


// Indicamos que nuestra clase va ser tipo controlador
@RestController
@RequestMapping("/v1")

public class DeltasController {

    @Autowired
    ProductsLogic productsLogic;


    @PostMapping("create_update_product")
    public ResponseEntity <?> CreateUpdate(
        @RequestBody HashMap<String, Object> body
        //@RequestHeader("env") String env
        // Mando a funcion body, env
        ){
        var result = ProductsLogic.ProductLogic(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(result); 
        }




    }



