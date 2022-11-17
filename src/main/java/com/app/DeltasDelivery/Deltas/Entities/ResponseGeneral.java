package com.app.DeltasDelivery.Deltas.Entities;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseGeneral {

    @JsonProperty("Code")
    public String code;

    @JsonProperty("Result")
    public String result;

    @JsonProperty("ResultDescription")
    public String resultDescription;

    @JsonProperty("body")
    public HashMap<String, Object> body;


}
