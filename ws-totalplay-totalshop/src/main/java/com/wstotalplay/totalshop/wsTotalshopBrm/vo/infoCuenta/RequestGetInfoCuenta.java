package com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetInfoCuenta {
    @JsonProperty("Login")
    public Login login;
    @JsonProperty("accountNo")
    public String accountNo;
}
