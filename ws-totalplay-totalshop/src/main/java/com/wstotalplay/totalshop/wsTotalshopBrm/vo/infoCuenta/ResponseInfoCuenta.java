package com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.ResultOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseInfoCuenta {
    @JsonProperty("Response")
    public ResultOperation resultOperation;
    @JsonProperty("AcountDescVo")
    public AcountDescVo acountDescVo;
}
