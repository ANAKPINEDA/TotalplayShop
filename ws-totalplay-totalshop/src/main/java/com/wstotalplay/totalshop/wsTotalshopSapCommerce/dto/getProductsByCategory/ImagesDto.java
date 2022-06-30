package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductsByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDto {
    @JsonProperty("format")
    public String format;
    @JsonProperty("imageType")
    public String imageType;
    @JsonProperty("url")
    public String url;
}
