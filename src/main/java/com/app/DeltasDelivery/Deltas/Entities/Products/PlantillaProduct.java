package com.app.DeltasDelivery.Deltas.Entities.Products;


public class PlantillaProduct {
    
    private Object id_product;
    private Object category;
    private Object id_restaurant;

    private Object descripcion;

    private Object price;

    private Object status;

    private String ban;


    public PlantillaProduct(String ban) {
        this.ban= ban;

    }


    public void setId_product(Object id_product) {

        if (id_product==null && ban.equals("1")) {
            this.id_product = "";
        }else{
            this.id_product = id_product;
        }
    }


    public void setCategory(Object category) {

        //Si es null la entrada (No agregaron el campo) y la bandera es 1 -Vamos a insertar
        if (category == null && ban.equals("1")) {
            this.category = "";
        }else{
            //En caso contrario mantenemos el dato que ingrese el usuario o el null - para que al convertirlo a json se elimine
            this.category = category;
        }

    }

    public void setId_restaurant(Object id_restaurant) {

        if (id_restaurant==null && ban.equals("1")) {
            this.id_restaurant = "";
        }else{
            this.id_restaurant = id_restaurant;
        }
    }


    public void setDescripcion(Object descripcion) {

        if (descripcion==null && ban.equals("1")) {
            this.descripcion = "";
        }else{
            this.descripcion = descripcion;
        }
    }

    public void setPrice(Object price) {

        if (price==null && ban.equals("1")) {
            this.price = "";
        }else{
            this.price = price;
        }
    }

    public void setStatus(Object status) {

        if (status==null && ban.equals("1")) {
            this.status = "";
        }else{
            this.status = status;
        }
    }

    public Object getId_product() {
        return id_product;
    }

    public Object getCategory() {
        return category;
    }

    public Object getId_restaurant() {
        return id_restaurant;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public Object getPrice() {
        return price;
    }

    public Object getStatus() {
        return status;
    }
}
