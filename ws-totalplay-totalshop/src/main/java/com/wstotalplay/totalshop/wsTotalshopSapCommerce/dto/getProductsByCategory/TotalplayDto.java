package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductsByCategory;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalplayDto {
    @JsonProperty("min")
    public String min;
    @JsonProperty("month")
    public String month;
}
