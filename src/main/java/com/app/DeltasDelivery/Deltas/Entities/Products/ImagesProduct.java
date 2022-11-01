package com.app.DeltasDelivery.Deltas.Entities.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesProduct {

    @JsonProperty("product")
    public String product="";

    @JsonProperty("thumbnail")
    public String thumbnail="";

}
