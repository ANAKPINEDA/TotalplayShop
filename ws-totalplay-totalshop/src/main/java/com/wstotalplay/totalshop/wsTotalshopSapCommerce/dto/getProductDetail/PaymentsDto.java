package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsDto {
    @JsonProperty("NPayments")
    public String nPayments;

    @JsonProperty("id")
    public String id;

    @JsonProperty("min")
    public String min;

    @JsonProperty("month")
    public String month;

    @JsonProperty("type")
    public String type;
}
