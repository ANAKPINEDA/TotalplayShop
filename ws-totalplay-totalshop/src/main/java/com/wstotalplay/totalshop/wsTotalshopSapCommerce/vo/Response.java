package com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @JsonProperty("Response")
    public ResultOperation resultOperation;
}
