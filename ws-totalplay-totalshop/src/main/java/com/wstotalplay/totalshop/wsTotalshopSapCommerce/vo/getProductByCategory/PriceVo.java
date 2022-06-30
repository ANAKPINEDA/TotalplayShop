package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceVo {
    @JsonProperty("amount")
    public String amount;
    @JsonProperty("color")
    public String color;
    @JsonProperty("price")
    public String price;
}
