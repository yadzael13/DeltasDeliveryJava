package com.app.DeltasDelivery.Deltas.Entities.Restaurants;

public class ImagesCommerce {
    private Object logo192Wx192H;

    private Object mapIcon;

    private Object storeImages;

    private Object ban;

    private Object env;

    private String dominioProd = "https://backoffice.c6exvb10-totalplay1-p1-public.model-t.cc.commerce.ondemand.com";
    private String dominioQa = "https://backoffice.c6exvb10-totalplay1-s1-public.model-t.cc.commerce.ondemand.com";

    public ImagesCommerce(String ban, String env) {
        this.ban= ban;
        this.env = env;
    }

    public void setLogo192Wx192H(Object logo192Wx192H) {

        if (logo192Wx192H==null && ban.equals("1")) {

            // Condiciones para dominio(Donde viven las imagenes)
            if (env.equals("prod")){
                this.logo192Wx192H = dominioProd +"";
            }
            if (env.equals("qa")){
                this.logo192Wx192H = dominioQa +"";
            }

        }else{

            if (logo192Wx192H!=null){
                // Condiciones para dominio(Donde viven las imagenes)
                if (env.equals("prod")){
                    this.logo192Wx192H = dominioProd +logo192Wx192H;
                }
                if (env.equals("qa")){
                    this.logo192Wx192H = dominioQa +logo192Wx192H;
                }
            }

        }
    }

    public void setMapIcon(Object mapIcon) {

        if (mapIcon==null && ban.equals("1")) {
            // Condiciones para dominio(Donde viven las imagenes)
            if (env.equals("prod")){
                this.mapIcon = dominioProd +"";
            }
            if (env.equals("qa")){
                this.mapIcon = dominioQa +"";
            }
        }else{
            if (mapIcon!=null){
                // Condiciones para dominio(Donde viven las imagenes)
                if (env.equals("prod")){
                    this.mapIcon = dominioProd +mapIcon;
                }
                if (env.equals("qa")){
                    this.mapIcon = dominioQa +mapIcon;
                }
            }

        }
    }

    public void setStoreImages(Object storeImages) {

        if (storeImages==null && ban.equals("1")) {
            // Condiciones para dominio(Donde viven las imagenes)
            if (env.equals("prod")){
                this.storeImages = dominioProd +"";
            }
            if (env.equals("qa")){
                this.storeImages = dominioQa +"";
            }
        }else{

            if (storeImages!=null){
                // Condiciones para dominio(Donde viven las imagenes)
                if (env.equals("prod")){
                    this.storeImages = dominioProd +storeImages;
                }
                if (env.equals("qa")){
                    this.storeImages = dominioQa +storeImages;
                }
            }


        }
    }

    public Object getLogo192Wx192H() {
        return logo192Wx192H;
    }

    public Object getMapIcon() {
        return mapIcon;
    }

    public Object getStoreImages() {
        return storeImages;
    }
}
