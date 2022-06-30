package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    @JsonProperty("date_end")
    public String dateEnd;

    @JsonProperty("date_start")
    public String dateStart;

    @JsonProperty("disccountPrice")
    public String disccountPrice;

    @JsonProperty("disccountPriceRounded")
    public String disccountPriceRounded;

    @JsonProperty("discount")
    public String discount;

    @JsonProperty("discountRounded")
    public String discountRounded;

    @JsonProperty("quantityDisccount")
    public String quantityDisccount;

    @JsonProperty("quantityDisccountRounded")
    public String quantityDisccountRounded;
}
