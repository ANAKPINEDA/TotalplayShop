package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductsByCategory;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsByCategoryDto {

    @JsonProperty("pagination")
    public PaginationDto pagination;

    @JsonProperty("products")
    public List<ProductsDto> listProducts;

    @JsonProperty("totalResults")
    public String totalResults;
}
