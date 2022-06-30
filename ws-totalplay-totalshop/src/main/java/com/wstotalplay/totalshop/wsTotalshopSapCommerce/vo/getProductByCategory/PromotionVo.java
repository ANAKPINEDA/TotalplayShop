package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionVo {
    @JsonProperty("discount")
    public String discount;
    @JsonProperty("exipiration")
    public String exipiration;
    @JsonProperty("disccountPrice")
    public String disccountPrice;
    @JsonProperty("quantityDisccount")
    public String quantityDisccount;

}
