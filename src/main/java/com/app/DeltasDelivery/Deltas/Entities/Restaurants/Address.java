package com.app.DeltasDelivery.Deltas.Entities.Restaurants;

public class Address {
    public Object formattedAddress;

    public Object postalCode;

    public Object streetname;

    public Object streetnumber;

    public Object town;

    public String ban;

    public Address(String ban){
        this.ban= ban;
    }

    public void setFormattedAddress(Object formattedAddress) {

        //Quiere crear- y lo que manda es null lo igualamos
        // En caso contrario toma el dato que ingresa el usuario o ya se el inicializado
        if (formattedAddress==null && ban.equals("1")) {
            this.formattedAddress = "";
        }else{
            this.formattedAddress = formattedAddress;
        }
    }

    public void setPostalCode(Object postalCode) {

        // Si mandan null (Significa que no manda el atributo) y quiere Insertar(1)
        // Inicializamos el dato con un valor para no mandar plantilla vacia

        if (postalCode==null && ban.equals("1")) {
            this.postalCode = "";
        }else{
            this.postalCode = postalCode;
        }
    }

    public void setStreetname(Object streetname) {

        if (streetname==null && ban.equals("1")) {
            this.streetname = "";
        }else{
            this.streetname = streetname;
        }
    }

    public void setStreetnumber(Object streetnumber) {

        if (streetnumber==null && ban.equals("1")) {
            this.streetnumber ="";
        }else{
            this.streetnumber = streetnumber;
        }
    }

    public void setTown(Object town) {

        if (town==null && ban.equals("1")) {
            this.town = "";
        }else{
            this.town = town;
        }
    }

    public Object getFormattedAddress() {
        return formattedAddress;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public Object getStreetname() {
        return streetname;
    }

    public Object getStreetnumber() {
        return streetnumber;
    }

    public Object getTown() {
        return town;
    }
}
