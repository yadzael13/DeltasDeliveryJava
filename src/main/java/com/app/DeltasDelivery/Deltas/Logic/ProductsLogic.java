package com.app.DeltasDelivery.Deltas.Logic;

import com.app.DeltasDelivery.Deltas.Entities.Products.ImagesProduct;
import com.app.DeltasDelivery.Deltas.Entities.Products.PlantillaProduct;
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class ProductsLogic {

    public ResponseGeneral Products(HashMap body, String env) {

        //Traemos el body - Objeto
        System.out.println("Nuestro request - ENTRADA -- JSON");
        System.out.println(body);


        //Creación
        if (env.equals("1")) {
            System.out.println("Creación PRODUCTO --------------------------------");
             MapeaDatosDinamicos(body, env);
            /// MapeaDatosEstaticos ---- SIEMPRE



            // Modificacion
        }else{
            System.out.println("Modificación PRODUCTO -----------------------------");

            //Modificamos - Solo campos que queremos modificar
            MapeaDatosDinamicos(body, env);
            /// MapeaDatosEstaticos ---- SIEMPRE

        }

        ResponseGeneral response = new ResponseGeneral();
        response.setCode("200");
        response.setResult("Producto Creado");
        response.setResultDescription("Se creo con normalidad el producto");

        return response;

    }

    public JSONObject MapeaDatosDinamicos(HashMap<String,String>bodyMap, String ban){

        JSONObject body = new JSONObject(bodyMap);

        System.out.println("ENTRO JSON convertido a Map");

        //Mandamos bandera
        PlantillaProduct platilla =new PlantillaProduct(ban);

       //Mapeamos platilla - EN CASO DE NO ENCOTRAR DATO MANDA NULL convertido en String
        platilla.setId_product(body.opt("code"));
        platilla.setCategory(body.opt("category"));
        //Code
        System.out.println("Code");
        System.out.println(body.opt("code"));

        platilla.setDescripcion(body.opt("descripcion"));
        platilla.setPrice(body.opt("price"));
        platilla.setStatus(body.opt("status"));

        //ImagesProduct images = new ImagesProduct(String.valueOf(body.opt("status")),String.valueOf(body.opt("status")));


        System.out.println("\n");
        System.out.println("NUESTRA SALIDA EN CONVERSION JSON------------ FIREBASE");
        JSONObject json = new JSONObject(platilla);
        System.out.println(json);
        return null;
    }
}
