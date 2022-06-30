package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaDto {
    @JsonProperty("img")
    public String img;
    @JsonProperty("video")
    public String video;
}
