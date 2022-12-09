package com.app.DeltasDelivery.Deltas.Logic;

import com.app.DeltasDelivery.Deltas.Entities.Products.ImagesProduct;
import com.app.DeltasDelivery.Deltas.Entities.Products.PlantillaProduct;
import com.app.DeltasDelivery.Deltas.Firebase.FirebaseMethods;
import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class ProductsLogic {

    public ResponseGeneral products(HashMap body, String env) {

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

    public ResponseGeneral productDelete(String comercio, String categoria, String product){
        ResponseGeneral resp = new ResponseGeneral();
        try {
            Boolean valid_prod_pricipal =FirebaseMethods.exist_prod_principal(comercio, product);
            Boolean valid_prod = FirebaseMethods.exist_prod(comercio, categoria, product);

            if(valid_prod_pricipal && valid_prod){
                //Eliminacion de producto a nivel principal
                FirebaseMethods.delete_product_principal(comercio, product);
                
                //Eliminacion de producto a nivel categoria
                FirebaseMethods.delete_product(comercio, categoria, product);
                resp.setCode("200");
                resp.setResult("Operacion Exitosa");
                resp.setResultDescription("Se ha eliminado el producto correctamente");
                
            } else if(!valid_prod_pricipal && valid_prod){
                //Eliminacion de producto a nivel categoria
                FirebaseMethods.delete_product(comercio, categoria, product);
                resp.setCode("205");
                resp.setResult("Operacion Exitosa");
                resp.setResultDescription( "El producto "+ product + " no existe a nivel principal, se elimino el producto a nivel productos por categoria");
                
            } else if(valid_prod_pricipal && !valid_prod){
                  //Eliminacion de producto a nivel pirncipal
                  FirebaseMethods.delete_product_principal(comercio, product);
                  resp.setCode("205");
                  resp.setResult("Operacion Exitosa");
                  resp.setResultDescription( "El producto "+" no existe a nivel categoria, se ha eliminado a nivel principal");
                
            }else{
                Exception e = new Exception();
                throw e;
            }
            
        } catch (Exception e) {
            Loggers.errorLog("ProductLogic -- productDelete", "Producto no encontrado en ningun nivel");

            resp.setCode("400");
            resp.setResult("Operacion incorrecta");
            resp.setResultDescription("Hubo un error al eliminar el producto Favor de verificar datos");
        }
        return resp;
    }
}
