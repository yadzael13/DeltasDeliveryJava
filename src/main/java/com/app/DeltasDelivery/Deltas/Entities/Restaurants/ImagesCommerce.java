package com.app.DeltasDelivery.Deltas.Entities.Restaurants;

public class ImagesCommerce {
    private Object logo192Wx192H;

    private Object mapIcon;

    private Object storeImages;

    private Object ban;

    private Object env;

    public ImagesCommerce(String ban, String env) {
        this.ban= ban;
        this.env = env;
    }

    public void setLogo192Wx192H(Object logo192Wx192H) {

        if (logo192Wx192H==null && ban.equals("1")) {
            this.logo192Wx192H = "";
        }else{

            if (logo192Wx192H!=null){
                // Condiciones para dominio(Donde viven las imagenes)
                if (env.equals("prod")){
                    this.logo192Wx192H = "https://backoffice.c6exvb10-totalplay1-p1-public.model-t.cc.commerce.ondemand.com" +logo192Wx192H;
                }
                if (env.equals("qa")){
                    this.logo192Wx192H = "https://backoffice.c6exvb10-totalplay1-s1-public.model-t.cc.commerce.ondemand.com" +logo192Wx192H;
                }
            }

        }
    }

    public void setMapIcon(Object mapIcon) {

        if (mapIcon==null && ban.equals("1")) {
            this.mapIcon = "";
        }else{
            if (mapIcon!=null){
                // Condiciones para dominio(Donde viven las imagenes)
                if (env.equals("prod")){
                    this.mapIcon = "https://backoffice.c6exvb10-totalplay1-p1-public.model-t.cc.commerce.ondemand.com" +mapIcon;
                }
                if (env.equals("qa")){
                    this.mapIcon = "https://backoffice.c6exvb10-totalplay1-s1-public.model-t.cc.commerce.ondemand.com" +mapIcon;
                }
            }

        }
    }

    public void setStoreImages(Object storeImages) {

        if (storeImages==null && ban.equals("1")) {
            this.storeImages = "";
        }else{

            if (storeImages!=null){
                // Condiciones para dominio(Donde viven las imagenes)
                if (env.equals("prod")){
                    this.storeImages = "https://backoffice.c6exvb10-totalplay1-p1-public.model-t.cc.commerce.ondemand.com" +storeImages;
                }
                if (env.equals("qa")){
                    this.storeImages = "https://backoffice.c6exvb10-totalplay1-s1-public.model-t.cc.commerce.ondemand.com" +storeImages;
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
