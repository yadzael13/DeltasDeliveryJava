package com.app.DeltasDelivery.Deltas.Controller;

//Librerias básicas
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Entities y logica
import com.app.DeltasDelivery.Deltas.Logic.ProductsLogic;
import com.app.DeltasDelivery.Deltas.Logic.RestaurantsLogic;

// Json


import lombok.var;

import java.util.HashMap;


// Indicamos que nuestra clase va ser tipo controlador
@RestController
@RequestMapping("/v1")

public class DeltasController {

    @Autowired
    ProductsLogic productsLogic;

    @Autowired
    RestaurantsLogic restaurantsLogic;

    @GetMapping("/create_product")
    public ResponseEntity <?> CreateProduct(
            @RequestBody HashMap body,
            @RequestHeader("env") String env
            // Mando a funcion body, env
    ){

        var result = productsLogic.Products(body,env);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    // Creacion restaurantes

    @GetMapping("/create_restaurant")
    public ResponseEntity <?> createRestaurant(
            @RequestBody HashMap body,
            @RequestHeader("env") String env,
            @RequestHeader("ban") String ban
            // Mando a funcion body, env
    ){
        var result = restaurantsLogic.restaurants(body,env,ban);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }


}
