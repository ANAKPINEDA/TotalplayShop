package com.wstotalplay.totalshop.wsTotalshopBrm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Args {
    @JsonProperty("etiqueta")
    public String etiqueta;
    @JsonProperty("elem")
    public String elem;
    @JsonProperty("valor")
    public String valor;
}
