package com.wstotalplay.totalshop.wsTotalshopBrm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultOperation {
    @JsonProperty("Result")
    public String result;
    @JsonProperty("ResultDescription")
    public String resultDescription;
    //Falta agregar un metodo para mensajes exitosos y fallo general los demas se agregan a manita
}
