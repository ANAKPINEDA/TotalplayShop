package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {
    @JsonProperty("amount")
    public String amount;

    @JsonProperty("amountRounded")
    public String amountRounded;

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("priceHasDiscount")
    public Boolean priceHasDiscount;

    @JsonProperty("priceWithDiscount")
    public String priceWithDiscount;

    @JsonProperty("priceWithDiscountRounded")
    public String priceWithDiscountRounded;
}
