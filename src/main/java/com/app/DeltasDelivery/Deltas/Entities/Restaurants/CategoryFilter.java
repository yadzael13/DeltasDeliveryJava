package com.app.DeltasDelivery.Deltas.Entities.Restaurants;

public class CategoryFilter {

    private Object id;

    private Object nameCategory;
    private Object nameComercio;
    private Object ban;

    public CategoryFilter(String ban){
        this.ban= ban;
    }

    public void setId(Object id) {

        //Quiere crear- y lo que manda es null lo igualamos
        // En caso contrario toma el dato que ingresa el usuario o ya se el inicializado
        if (id==null && ban.equals("1")) {
            this.id = "";
        }else{
            this.id = id;
        }
    }

    public void setNameCategory(Object nameCategory) {

        if (nameCategory==null && ban.equals("1")) {
            this.nameCategory = "";
        }else{
            this.nameCategory = nameCategory;
        }
    }

    public void setNameComercio(Object nameComercio) {

        if (nameComercio==null && ban.equals("1")) {
            this.nameComercio = "";
        }else{
            this.nameComercio = nameComercio;
        }
    }

    public Object getId() {
        return id;
    }

    public Object getNameCategory() {
        return nameCategory;
    }

    public Object getNameComercio() {
        return nameComercio;
    }
}
