package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentVo {
    @JsonProperty("id")
    public String id;
    @JsonProperty("amount")
    public String amount;
    @JsonProperty("price")
    public String price;
    @JsonProperty("brand")
    public String brand;
    @JsonProperty("name")
    public String name;
    @JsonProperty("complement")
    public String complement;
    @JsonProperty("video")
    public String video;
    @JsonProperty("images")
    public String images;
    @JsonProperty("promotion")
    public PromotionVo promotion;
    @JsonProperty("summary")
    public String summary;
}
