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

    // TERMINA BRENDA ----- CREA Y MODIFICA RESTAURANTE
    @PostMapping ("/create_restaurant")
    public ResponseEntity <?> createRestaurant(
            @RequestBody HashMap body,
            @RequestHeader("env") String env,
            @RequestHeader("ban") String ban
            // Mando a funcion body, env
    ){

        ResponseGeneral res = new ResponseGeneral();

        try {

            // ********* Tenemos que colocar loggs de los bodys que entran **********
            Loggers.infoLog("/create_restaurant","Request = "+body.toString());

            // ********** Validacion Datos Obligatorios **********





            var result = restaurantsLogic.restaurants(body,env,ban);

            // ------------ MEJORA obtenermos CODE en string y en el controlador se convierte en HttpStatus
            int code = Integer.parseInt(result.getCode());
            return ResponseEntity.status(HttpStatus.valueOf(code)).body(result);

        }catch (Exception e){
            // ********* Tenemos que colocar obtener error personalizado **********
            Loggers.errorLog("create_restaurant - Controlador ",e.toString());

            res.setCode("500");
            res.setResult("Error en la operación");
            res.setResultDescription("Favor de validar Servicio");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }


    /// TERMINA BRENDA -------------------------------- CREA Y MODIFICA CATEGORIA
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


    // PENDIENTE --------------------------------------- CREA Y MODIFICA PRODUCTO
    @PostMapping("/create_product")
    public ResponseEntity <?> createProduct(
            @RequestBody HashMap body,
            @RequestHeader("env") String env
            // Mando a funcion body, env
    ){

        var result = productsLogic.products(body,env);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }






}
