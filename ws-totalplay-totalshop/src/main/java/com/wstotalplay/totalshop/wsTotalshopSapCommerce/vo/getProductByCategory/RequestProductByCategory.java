package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductByCategory {
    @JsonProperty("Login")
    public Login login;
    @JsonProperty("idCategory")
    public String idCategory;
    @JsonProperty("page")
    public String page;
    @JsonProperty("pageSize")
    public String pageSize;
}
