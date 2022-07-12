package com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneVo {
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("type")
    public String type;
    @JsonProperty("description")
    public String description;
}
