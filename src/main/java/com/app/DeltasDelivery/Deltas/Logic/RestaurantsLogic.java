package com.app.DeltasDelivery.Deltas.Logic;


import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.Address;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.CategoryFilter;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.DatosDirectos;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.ImagesCommerce;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import java.util.HashMap;

@Service

public class RestaurantsLogic {

    public ResponseGeneral restaurants(HashMap body, String env, String ban) {

        //Traemos el body - Objeto
        System.out.println("Nuestro request - ENTRADA -- JSON");
        System.out.println(body);


        //Creación
        if (ban.equals("1")) {
            System.out.println("Creación RESTAURANTE --------------------------------");
            MapeaDatosDinamicos(body, ban, env);
            /// MapeaDatosEstaticos ---- SIEMPRE

            // Modificacion
        }else{
            System.out.println("Modificación RESTAURANTE -----------------------------");

            //Modificamos - Solo campos que queremos modificar
            MapeaDatosDinamicos(body, ban, env);
            /// MapeaDatosEstaticos ---- SIEMPRE

        }

        ResponseGeneral response = new ResponseGeneral();
        response.setCode("200");
        response.setResult("Producto Creado");
        response.setResultDescription("Se creo con normalidad el producto");

        return response;
    }

    public JSONObject MapeaDatosDinamicos(HashMap<String,String>bodyMap, String ban, String env){


        System.out.println("Map DE ENTRADA -------------------------------");
        System.out.println(bodyMap);


        //------- TodoS los Datos del comercio -----------

        // ---------- Con niveles
        JSONObject restaurant = new JSONObject();

        restaurant.put("address",address(bodyMap,ban));
        restaurant.put("categoryFilter",categoryFilter(bodyMap,ban));
        restaurant.put("imagesCommerce",imagesCommerce(bodyMap,ban,env));


        // ----------- Directos
        //Convertimos la entrada de Map a JSON
        JSONObject body = new JSONObject(bodyMap);

        DatosDirectos datos = new DatosDirectos(ban);

        // Sets -- Lleva validaciones null
        datos.setDescription(body.opt("description"));
        datos.setNameCommerce(body.opt("displayname"));
        datos.setStatus(body.opt("status"));
        datos.setTypeCommerce(body.opt("typeCommerce"));

        // Gets
        restaurant.put("description",datos.getDescription());
        restaurant.put("nameCommerce",datos.getNameCommerce());
        restaurant.put("status",datos.getStatus());
        restaurant.put("typeCommerce",datos.getTypeCommerce());

        System.out.println("\n");
        System.out.println("NUESTRA SALIDA EN CONVERSION JSON------------ FIREBASE");
        System.out.println(restaurant);

        // Validamos que nuestro JSON RESTAURANT tenga Datos

        if (restaurant.length()!=0) {
            return restaurant;
        }else{
            return null;
        }
    }

    public JSONObject address(HashMap<String,String>bodyMap, String ban){

        //Convertimos la entrada de Map a JSON
        JSONObject body = new JSONObject(bodyMap);

        //Mandamos a llamar objeto Address
        Address addressObj = new Address(ban);

        // Telefono -- Se agreega hasta campos directos
        DatosDirectos datos = new DatosDirectos(ban);

        // Si mi request contiene address - Mapeo con seguridad -- Manejamos los null para campos que no manden

        // Verificamos que existan las llaves para mapear con seguridad
        Boolean status = bodyMap.containsKey("address");

        //Tambien la llave debe tener info
        if (status && body.optJSONObject("address").length()!=0) {

            //Datos apartado address
            addressObj.setFormattedAddress(body.optJSONObject("address").opt("formattedAddress"));
            addressObj.setStreetname(body.optJSONObject("address").opt("streetname"));
            addressObj.setStreetnumber(body.optJSONObject("address").opt("streetnumber"));
            addressObj.setTown(body.optJSONObject("address").opt("town"));
            addressObj.setPostalCode(body.optJSONObject("address").opt("postalCode"));

            // Datos directos
            datos.setPhone(body.optJSONObject("address").opt("phone"));

        }else{
            // Mandamos plantilla - vacia(requerido para crear restaurant) - La validacion esta dentro del objeto
            // Colocamos null para que se llene la plantilla
            addressObj.setFormattedAddress(null);
            addressObj.setStreetname(null);
            addressObj.setStreetnumber(null);
            addressObj.setTown(null);
            addressObj.setPostalCode(null);

            //Datos directos
            datos.setPhone(null);
        }


        System.out.println("Imprimimos en JSON ----- Address");
        JSONObject addressJson = new JSONObject(addressObj);
        System.out.println(addressJson);

        //Verificamos si esta lleno el json
        if (addressJson.length()!=0) {
            return addressJson;
        }else{
            return null;
        }

    }

    public JSONObject categoryFilter(HashMap<String,String>bodyMap, String ban){

        //Convertimos la entrada de Map a JSON
        JSONObject body = new JSONObject(bodyMap);

        //Mandamos a llamar objeto CategoryFilter
        CategoryFilter category = new CategoryFilter(ban);

        Boolean status = bodyMap.containsKey("warehousesDetail");

        // Colocamos un try ya que es un azar saber si ellos mandaran la info - Hasta ese nivel
        try{
            //id categoria
            String cat_id = String.valueOf(body.optJSONArray("warehousesDetail").optJSONObject
            (0).optJSONObject("vendor").optJSONArray("categoriesFood").optJSONObject(1).opt("id"));

            category.setId(cat_id);

        }catch (Exception e){
            category.setId(null);

        }
        // Colocamos un try ya que es un azar saber si ellos mandaran la info - Hasta ese nivel
        try{
            //nombre categoria
            String cat_name = String.valueOf(body.optJSONArray("warehousesDetail").optJSONObject
            (0).optJSONObject("vendor").optJSONArray("categoriesFood").optJSONObject(1).opt("name"));

            category.setNameCategory(cat_name);

        }catch (Exception e){
            category.setNameCategory(null);

        }

        // Colocamos un try ya que es un azar saber si ellos mandaran la info - Hasta ese nivel
        try{
            //nombre comercio
            String name_Comercio = String.valueOf(body.optJSONArray("warehousesDetail").optJSONObject
                    (0).optJSONObject("vendor").opt("name"));

            category.setNameComercio(name_Comercio);

        }catch (Exception e){
            category.setNameComercio(null);

        }

        System.out.println("Imprimimos en JSON ----- Category");
        JSONObject categoryJson = new JSONObject(category);
        System.out.println(categoryJson);

        //Verificamos si esta lleno el json
        if (categoryJson.length()!=0) {
            return categoryJson;
        }else{
            return null;
        }

    }

    public JSONObject imagesCommerce(HashMap<String,String>bodyMap, String ban, String env){

        //Convertimos la entrada de Map a JSON
        JSONObject body = new JSONObject(bodyMap);

        ImagesCommerce images = new ImagesCommerce(ban,env);


        //MapIcon
        // Verificamos que existan las llaves para mapear con seguridad
        // MapIcon y logo192Wx192H --- SON LO MISMO
        Boolean s1 = bodyMap.containsKey("mapIcon");
        //Tambien la llave debe tener info
        if (s1  && body.optJSONObject("mapIcon").length()!=0) {
            images.setMapIcon(body.optJSONObject("mapIcon").opt("url"));
            images.setLogo192Wx192H(body.optJSONObject("mapIcon").opt("url"));
        }else{
            images.setMapIcon(null);
            images.setLogo192Wx192H(null);
        }

        //StoreImages
        // Verificamos que existan las llaves para mapear con seguridad
        Boolean s2 = bodyMap.containsKey("storeImages");
        //Tambien la llave debe tener info
        if (s2 && body.optJSONObject("storeImages").length()!=0) {
            images.setStoreImages(body.optJSONArray("storeImages").optJSONObject(0).opt("url"));
        }else{
            images.setStoreImages(null);
        }

        System.out.println("Imprimimos en JSON ----- Address");
        JSONObject imagesJson = new JSONObject(images);
        System.out.println(imagesJson);

        //Verificamos si esta lleno el json
        if (imagesJson.length()!=0) {
            return imagesJson;
        }else{
            return null;
        }

    }
}
