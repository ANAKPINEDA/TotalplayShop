package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductsByCategory;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto {
    @JsonProperty("count")
    public int count;

    @JsonProperty("page")
    public int page;

    @JsonProperty("totalCount")
    public int totalCount;

    @JsonProperty("totalPages")
    public int totalPages;
}
