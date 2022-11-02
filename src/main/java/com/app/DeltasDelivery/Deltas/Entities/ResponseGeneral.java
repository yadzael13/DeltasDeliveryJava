package com.app.DeltasDelivery.Deltas.Entities;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseGeneral {

    @JsonProperty("Code")
    public String code;

    @JsonProperty("Result")
    public String result;

    @JsonProperty("ResultDescription")
    public String resultDescription;

    @JsonProperty("body")
    public JSONObject body;


}
