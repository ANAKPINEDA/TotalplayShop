package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsVo {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("media")
    public MediaVo media;
    @JsonProperty("price")
    public PriceVo priceVo;
    @JsonProperty("promotion")
    public PromotionVo promotion;
    @JsonProperty("totalplay")
    public TotalplayVo totalplay;
    @JsonProperty("summary")
    public String summary;
}
