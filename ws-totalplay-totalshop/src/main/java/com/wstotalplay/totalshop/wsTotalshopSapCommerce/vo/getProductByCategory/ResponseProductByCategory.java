package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.ResultOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ResponseProductByCategory{
    @JsonProperty("Response")
    public ResultOperation resultOperation;
    @JsonProperty("banner")
    public Bannervo banner;
    @JsonProperty("products")
    public List<ProductsVo> listProducts;
    @JsonProperty("page")
    public int page;
    @JsonProperty("totalPages")
    public int totalPages;
}
