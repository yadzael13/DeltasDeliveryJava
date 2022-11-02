package com.app.DeltasDelivery.Deltas.Controller;

//Librerias básicas
import com.app.DeltasDelivery.Deltas.Entities.Products.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create_product")
    public ResponseEntity <?> CreateProduct(
            @RequestBody InputProduct body
            //@RequestHeader("env") String env
            // Mando a funcion body, env
    ){

        var result = productsLogic.Products(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);



    }

    @GetMapping("/get_json")
    public ResponseEntity <?> getJson(){

        var result = jsonLogic.getJson();
    
        return ResponseEntity.status(HttpStatus.CREATED).body(result);



    }


}
