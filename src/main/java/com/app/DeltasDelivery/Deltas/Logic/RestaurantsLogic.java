package com.app.DeltasDelivery.Deltas.Logic;

//Entities
import com.app.DeltasDelivery.Deltas.Entities.ResponseGeneral;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.Address;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.CategoryFilter;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.DatosDirectos;
import com.app.DeltasDelivery.Deltas.Entities.Restaurants.ImagesCommerce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

//Tools
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import lombok.var;
import com.app.DeltasDelivery.Deltas.Tools.Loggers;
import com.google.cloud.firestore.GeoPoint;

@Service

public class RestaurantsLogic {

    public ResponseGeneral restaurants(HashMap body, String env, String ban) throws JsonProcessingException {

        //Traemos el body - Objeto
        System.out.println("Nuestro request - ENTRADA -- JSON");
        System.out.println(body);
        ResponseGeneral response = new ResponseGeneral();


        // ********** Tenemos que poner validaciones para datos obligatorios **********
        //**********  Tenemos que poner consulta a firebase para extraer banderas (Existe comercio o no)**********


        HashMap<String, Object> bodyRestaurantFirebase = new HashMap();



        //Creación
        if (ban.equals("1")) {

            System.out.println("Inserción RESTAURANTE -----------------------------");

            // ********** Tenemos que unir 2 cuerpos Dinamicos y Estaticos **********
            bodyRestaurantFirebase.putAll(MapeaDatosDinamicos(body, ban, env));
            bodyRestaurantFirebase.putAll(metodos_estaticos(body));

            System.out.println("CUERPO Final DEL RESTAURANTE MAP------ INSERTAR");
            System.out.println(bodyRestaurantFirebase);

            System.out.println("CUERPO Final DEL RESTAURANTE JSON------ INSERTAR");
            JSONObject bodyJson1 = new JSONObject(bodyRestaurantFirebase);
            System.out.println(bodyJson1);


            // ********** Mandarlos a conexion firebse creacion con campos requeridos **********

            response.setCode("200");
            response.setResult("Producto Modificado");
            response.setResultDescription("Se CREO con normalidad el producto");

        }else{
            System.out.println("Modificación RESTAURANTE -----------------------------");

            // ********** Tenemos que unir 2 cuerpos Dinamicos y Estaticos **********
            bodyRestaurantFirebase.putAll(MapeaDatosDinamicos(body, ban, env));
            bodyRestaurantFirebase.putAll(metodos_estaticos(body));

            System.out.println("CUERPO Final DEL RESTAURANTE MAP------ MODIFICAR");
            System.out.println(bodyRestaurantFirebase);

            System.out.println("CUERPO Final DEL RESTAURANTE JSON------ MODIFICAR");
            JSONObject bodyJson2 = new JSONObject(bodyRestaurantFirebase);
            System.out.println(bodyJson2);

            // ********** Mandarlos a conexion firebse Modificacion con campos requeridos **********


            response.setCode("200");
            response.setResult("Producto Modificado");
            response.setResultDescription("Se Modifico con normalidad el producto");

        }


        return response;
    }

    //Datos Dinamicos
    public HashMap<String, Object>  MapeaDatosDinamicos(HashMap<String,String>bodyMap, String ban, String env) throws JsonProcessingException {


        System.out.println("Map DE ENTRADA -------------------------------");
        System.out.println(bodyMap);


        //------- TodoS los Datos del comercio -----------

        // ---------- Con niveles
        HashMap<String, Object> restaurant = new HashMap();

        //Va cada nivel lleva su propia lógica
        //Se agregan a cuerpo principal del restaurante -------------------RESTAURANTE
        restaurant.put("address",address(bodyMap,ban));
        restaurant.put("categoryFilter",categoryFilter(bodyMap,ban));
        restaurant.put("imagesCommerce",imagesCommerce(bodyMap,ban,env));


        // ----------- Directos
        //Convertimos la entrada de Map a JSON
        JSONObject body = new JSONObject(bodyMap);
        DatosDirectos datos = new DatosDirectos(ban);
        //Llamamos a datos directos ya que internamente se van
        // validando para modificar o insertar
        // Sets -- Lleva validaciones null
        datos.setDescription(body.opt("description"));
        datos.setNameCommerce(body.opt("displayName"));
        datos.setStatus(body.opt("status"));
        datos.setName(nameVendor(bodyMap));
        datos.setPhone(phoneAddress(bodyMap));

        //Se agregan a cuerpo principal del restaurante -------------------RESTAURANTE
        restaurant.put("description",datos.getDescription());
        restaurant.put("nameCommerce",datos.getNameCommerce());
        restaurant.put("status",datos.getStatus());
        restaurant.put("name",datos.getName());
        restaurant.put("phone",datos.getPhone());

        //Datos en duro para restaurante
        restaurant.put("typeCommerce","Restaurante");
        restaurant.put("score",0);

        System.out.println("\n");
        System.out.println("NUESTRA SALIDA EN CONVERSION JSON------------ FIREBASE");
        System.out.println(restaurant);

        // Validamos que nuestro JSON RESTAURANT tenga Datos

        if (restaurant.size()!=0) {
            //Convertimos nuestro json a hashMap
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



        }else{
            // Mandamos plantilla - vacia(requerido para crear restaurant) - La validacion esta dentro del objeto
            // Colocamos null para que se llene la plantilla
            addressObj.setFormattedAddress(null);
            addressObj.setStreetname(null);
            addressObj.setStreetnumber(null);
            addressObj.setTown(null);
            addressObj.setPostalCode(null);

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
        if (s2 && body.optJSONArray("storeImages").optJSONObject(0).length()!=0) {
            System.out.println("Entro a la condicion");
            images.setStoreImages(body.optJSONArray("storeImages").optJSONObject(0).opt("url"));
        }else{
            images.setStoreImages(null);
        }

        System.out.println("Imprimimos en JSON ----- Imagenes");
        JSONObject imagesJson = new JSONObject(images);
        System.out.println(imagesJson);

        //Verificamos si esta lleno el json
        if (imagesJson.length()!=0) {
            return imagesJson;
        }else{
            return null;
        }

    }

    public String nameVendor(HashMap<String,String>bodyMap){

        try{
            JSONObject body = new JSONObject(bodyMap);
            String namevendor;
            //nombre comercio
            namevendor = String.valueOf(body.optJSONArray("warehousesDetail").optJSONObject
                    (0).optJSONObject("vendor").opt("name"));
            return namevendor;

        }catch (Exception e){
            return null;

        }

    }

    public String phoneAddress(HashMap<String,String>bodyMap){

        try{
            JSONObject body = new JSONObject(bodyMap);
            String phone;
            //nombre comercio
            phone = String.valueOf(body.optJSONObject("address").opt("phone"));
            return phone;

        }catch (Exception e){
            return null;

        }

    }


    //Datos estaticos

    public static HashMap<String, Object> metodos_estaticos(HashMap<String, Object> body){

        System.out.println("Esta entrando en horarios");
        System.out.println(body);

        try {
            HashMap<String, Object> ret = new HashMap<>();
            String name = (String) body.get("name");

            //Horario
            HashMap sch = (HashMap) body.get("openingHours");
            List<HashMap<String, Object>> schedule = (List<HashMap<String, Object>>) sch.get("weekDayOpeningList");
            schedule = horario(schedule);

            //GeoPoint
            HashMap<String, Double> geo = (HashMap<String, Double>) body.get("geoPoint");
            GeoPoint geoPoint = new GeoPoint(geo.get("latitude"), geo.get("longitude"));

            //Obtener nombre desde vendor --- Correción es name del 1° nivel
            //List listVendor = (List) body.get("warehousesDetail");
            //HashMap auxVendor = (HashMap) listVendor.get(0);
            //auxVendor = (HashMap) auxVendor.get("vendor");
            //String nameVendor = (String) auxVendor.get("name");

            //Lo comento por que no es un dato obligatorio :c
            //ret.put("name", nameVendor);
            ret.put("geoPoint", geoPoint);
            ret.put("schedule", schedule);
            return ret;
        } catch (Exception e) {
            Loggers.infoLog("Metodos estaticos -Tools", e.toString());
            //Nota. Va a tronar y regresar null cuando no encuentre los datos obligatorios - Horario, geopoint, name(vendor)
            return null;
        }

    }
    public static List<HashMap<String, Object>>horario(List<HashMap<String, Object>> schedule2){


        try{
            List<HashMap<String, Object>> Schedule = new ArrayList<>();
            for(HashMap<String, Object> el : schedule2){
                var aux = outp(el);
                Schedule.add(aux);
            }
            return Schedule;
        } catch(Exception e){
            Loggers.errorLog("HorarioLogic - Tools", e.toString());
            return null;
        }


    }

    public static HashMap<String, Object> outp(HashMap<String, Object> input){
        HashMap<String, Object> body = new HashMap<>();

        HashMap<String, Object> openingTime_hash = (HashMap<String, Object>) input.get("openingTime");
        String openingTime = (String) openingTime_hash.get("formattedHour");

        HashMap<String, Object> closingTime_hash = (HashMap<String, Object>) input.get("closingTime");
        String closingTime = (String) closingTime_hash.get("formattedHour");

        String day = (String) input.get("week_day");
        day = day_funct(day);

        Boolean closed = (Boolean) input.get("closed");

        if(closed){
            body.put("day", day);
            body.put("closed", closed);
            return body;
        }

        body.put("openingTime", openingTime);
        body.put("closingTime", closingTime);
        body.put("day", day);
        body.put("closed", closed);
        return body;
    }

    public static String day_funct(String given_day){
        String ret = "";
        switch (given_day) {
            case "lun.":
                ret = "Lunes";
                break;

            case "mar.":
                ret = "Martes";
                break;

            case "mie.":
                ret = "Miércoles";
                break;

            case "mié.":
                ret = "Miércoles";
                break;

            case "jue.":
                ret = "Jueves";
                break;

            case "vie.":
                ret = "Viernes";
                break;
            case "sab.":
                ret = "Sábado";
                break;
            case "sáb.":
                ret = "Sábado";
                break;
            default:
                break;
        }
        return ret;
    }
}
