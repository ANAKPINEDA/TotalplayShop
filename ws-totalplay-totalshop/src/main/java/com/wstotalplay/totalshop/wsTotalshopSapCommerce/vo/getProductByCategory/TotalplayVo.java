package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalplayVo {
    @JsonProperty("min")
    public String min;
    @JsonProperty("month")
    public String month;
}
