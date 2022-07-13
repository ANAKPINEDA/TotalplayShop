package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getToken;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.ResultOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetToken{
    @JsonProperty("Response")
    public ResultOperation resultOperation;
    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("token_type")
    public String tokenType;
    @JsonProperty("expires_in")
    public String expiresIn;
    @JsonProperty("scope")
    public String scope;
}
