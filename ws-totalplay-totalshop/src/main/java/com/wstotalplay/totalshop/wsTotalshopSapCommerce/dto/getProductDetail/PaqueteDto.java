package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteDto {
    @JsonProperty("alto")
    public double alto;

    @JsonProperty("ancho")
    public double ancho;

    @JsonProperty("esPaquete")
    public boolean esPaquete;

    @JsonProperty("largo")
    public double largo;

    @JsonProperty("peso")
    public double peso;
}
