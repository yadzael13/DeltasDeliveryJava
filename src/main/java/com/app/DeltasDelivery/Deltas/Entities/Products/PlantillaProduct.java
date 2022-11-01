package com.app.DeltasDelivery.Deltas.Entities.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PlantillaProduct {

    @JsonProperty("id_producto")
    public String id_product;

    @JsonProperty("categoria")
    public String category;

    @JsonProperty("id_restaurant")
    public String id_restaurant;

    @JsonProperty("nombre")
    public String nombre;

    @JsonProperty("edad")
    public String edad;

    @JsonProperty("boolean")
    public Boolean bolano;

}
