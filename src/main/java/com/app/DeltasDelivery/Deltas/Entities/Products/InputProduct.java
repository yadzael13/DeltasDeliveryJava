package com.app.DeltasDelivery.Deltas.Entities.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class InputProduct {

    @JsonProperty("code")
    public String code;

    @JsonProperty("category")
    public String category;

    @JsonProperty("id_restaurant")
    public String id_restaurant;

    @JsonProperty("nombre")
    public String nombre;

    @JsonProperty("edad")
    public int edad;








}
