package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductsByCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.MultimediaDto;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.PriceDto;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.PromotionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    @JsonProperty("brand")
    public String brand;

    @JsonProperty("description")
    public String description;

    @JsonProperty("id")
    public String id;

    @JsonProperty("id_category")
    public String idCategory;

    @JsonProperty("images")
    public List<ImagesDto> listImages;

    @JsonProperty("msi")
    public MsiDto msi;

    @JsonProperty("multimedia")
    public MultimediaDto multimedia;

    @JsonProperty("price")
    public PriceDto price;

    @JsonProperty("promotion")
    public PromotionDto promotion;

    @JsonProperty("sku")
    public String sku;

    @JsonProperty("stock")
    public String stock;

    @JsonProperty("summary")
    public String summary;

    @JsonProperty("title")
    public String title;

    @JsonProperty("totalplay")
    public TotalplayDto totalplay;



}
