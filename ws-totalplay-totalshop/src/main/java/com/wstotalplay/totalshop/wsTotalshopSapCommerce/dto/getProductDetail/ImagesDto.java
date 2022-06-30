package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDto {
    @JsonProperty("altText")
    public String altText;

    @JsonProperty("format")
    public String format;

    @JsonProperty("galleryIndex")
    public int galleryIndex;

    @JsonProperty("galleryQualifier")
    public String galleryQualifier;

    @JsonProperty("imageType")
    public String imageType;

    @JsonProperty("originalImageCode")
    public String originalImageCode;

    @JsonProperty("url")
    public String url;
}
