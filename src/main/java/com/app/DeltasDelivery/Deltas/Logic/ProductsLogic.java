package com.app.DeltasDelivery.Deltas.Logic;

import com.app.DeltasDelivery.Deltas.Entities.Products.*;
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class ProductsLogic {

    public ResponseGeneral Products(InputProduct body) {

        //Traemos el body - Objeto
        System.out.println("Nuestro request - ENTRADA -- TIPO OBJETO");
        System.out.println(body);

        //Iniciamos proceso
        // Mapeamos nuestra plantilla(Ya que solo requerimos ciertos datos del body)

        PlantillaProduct platilla =new PlantillaProduct();

        platilla.setId_product(body.getCode());
        platilla.setCategory(body.getCategory());
        platilla.setId_restaurant(body.getId_restaurant());
        platilla.setNombre(body.getNombre());
        String edad = String.valueOf(body.getEdad());
        platilla.setEdad(edad);

        System.out.println("Nuestro salida");
        System.out.println("NUESTRA PLATILLA EN OBJETO");
        System.out.println(platilla);

        System.out.println("NUESTRA SALIDA EN CONVERSION JSON");

        JSONObject json = new JSONObject(platilla);
        /// YA QUEDO INGRESO DE LA INFORMACION
        System.out.println(json);


        // Modificacion tema - DEBEMOS MANDAR SOLO LO QUE ELLOS LLENEN(NO VACIOS)
        // Mandar campos que solo se quieren modificar
        // dato por =""

        ResponseGeneral response = new ResponseGeneral();
        response.setCode("200");
        response.setResult("Producto Creado");
        response.setResultDescription("Se creo con normalidad el producto");

        return response;

    }


}
