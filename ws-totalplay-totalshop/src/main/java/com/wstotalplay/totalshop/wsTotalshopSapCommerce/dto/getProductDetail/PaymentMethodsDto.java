package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodsDto {
    @JsonProperty("name")
    public String name;
    @JsonProperty("payments")
    public List<PaymentsDto> listPayments;
}
