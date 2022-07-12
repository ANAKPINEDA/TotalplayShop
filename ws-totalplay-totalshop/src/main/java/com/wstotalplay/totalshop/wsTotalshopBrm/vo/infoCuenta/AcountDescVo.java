package com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcountDescVo{
    @JsonProperty("acount")
    public String acount;
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public String status;
    @JsonProperty("tipoRelojCobranza")
    public String tipoRelojCobranza;
    @JsonProperty("businessUnit")
    public String businessUnit;
    @JsonProperty("businessDescription")
    public String businessDescription;
    @JsonProperty("taxRegime")
    public String taxRegime;
    @JsonProperty("taxRegimeDescription")
    public String taxRegimeDescription;
    @JsonProperty("contractType")
    public String contractType;
    @JsonProperty("currency")
    public String currency;
    @JsonProperty("currencyDescription")
    public String currencyDescription;
    @JsonProperty("cluster")
    public String cluster;
    @JsonProperty("segmentation")
    public String segmentation;
    @JsonProperty("descSegmentation")
    public String descSegmentation;
    @JsonProperty("behaivorScore")
    public String behaivorScore;
    @JsonProperty("dn")
    public String dn;
    @JsonProperty("creationDateAcount")
    public String creationDateAcount;
    @JsonProperty("idPackage")
    public String idPackage;
    @JsonProperty("packageDescription")
    public String packageDescription;
    @JsonProperty("rent")
    public String rent;
    @JsonProperty("prepaid")
    public String prepaid;
    @JsonProperty("address")
    public List<AddressVo> listAddress;
}
