package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDto {
    @JsonProperty("catalog")
    public String catalog;
    @JsonProperty("catalogVersion")
    public String catalogVersion;
    @JsonProperty("code")
    public String code;
}
