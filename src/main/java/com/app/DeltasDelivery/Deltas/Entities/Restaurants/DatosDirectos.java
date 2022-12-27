package com.app.DeltasDelivery.Deltas.Entities.Restaurants;

public class DatosDirectos {

    public Object description;
    public Object nameCommerce;
    public Object phone;
    public Object score;
    public Object status;
    public Object name;

    public Object ban;

    public DatosDirectos(String ban) {
        this.ban= ban;

    }

    public void setDescription(Object description) {
        if (description==null && ban.equals("1")) {
            this.description = "";
        }else{
            this.description = description;
        }
    }


    public void setNameCommerce(Object nameCommerce) {
        if (nameCommerce==null && ban.equals("1")) {
            this.nameCommerce = "";
        }else{
            this.nameCommerce = nameCommerce;
        }
    }

    public void setPhone(Object phone) {

        if (phone==null && ban.equals("1")) {
            this.phone = "";
        }else{
            this.phone = phone;
        }
    }

    public void setStatus(Object status) {

        if (status==null && ban.equals("1")) {
            this.status = "";
        }else{
            this.status = status;
        }
    }




    public void setName(Object name) {
        if (name==null && ban.equals("1")) {
            this.name = "";
        }else{
            this.name = name;
        }
    }

    public void setScore(Object score) {
        this.score = score;

    }

    public Object getDescription() {
        return description;
    }


    public Object getNameCommerce() {
        return nameCommerce;
    }



    public Object getPhone() {
        return phone;
    }

    public Object getScore() {
        return score;
    }



    public Object getStatus() {
        return status;
    }


    public Object getName() {
        return name;
    }
}
