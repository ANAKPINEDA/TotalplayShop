package com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVo {
    @JsonProperty("id")
    public String id;
    @JsonProperty("idDescription")
    public String idDescription;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("middleName")
    public String middleName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("conpany")
    public String conpany;
    @JsonProperty("title")
    public String title;
    @JsonProperty("gender")
    public String gender;
    @JsonProperty("birthday")
    public String birthday;
    @JsonProperty("street")
    public String street;
    @JsonProperty("autdoorNum")
    public String autdoorNum;
    @JsonProperty("city")
    public String city;
    @JsonProperty("colony")
    public String colony;
    @JsonProperty("delegation")
    public String delegation;
    @JsonProperty("country")
    public String country;
    @JsonProperty("email")
    public String email;
    @JsonProperty("state")
    public String state;
    @JsonProperty("zip")
    public String zip;
    @JsonProperty("phones")
    public List<PhoneVo> listPhones;
}
