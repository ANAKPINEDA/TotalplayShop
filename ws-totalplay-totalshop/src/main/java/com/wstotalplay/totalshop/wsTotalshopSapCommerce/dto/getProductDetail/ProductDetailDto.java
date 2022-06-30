package com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductDetail;

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
public class ProductDetailDto {

    @JsonProperty("averageRating")
    public String averageRating;

    @JsonProperty("brand")
    public String brand;

    @JsonProperty("categories")
    public List<CategoriesDto> listCategories;

    @JsonProperty("costoEnvio")
    public String costoEnvio;

    @JsonProperty("cpEnvio")
    public String cpEnvio;

    @JsonProperty("deliveryDays")
    public int deliveryDays;

    @JsonProperty("description")
    public String description;

    @JsonProperty("descriptionPlainText")
    public String descriptionPlainText;

    @JsonProperty("envioGratis")
    public boolean envioGratis;

    @JsonProperty("envioProveedor")
    public boolean envioProveedor;

    @JsonProperty("id")
    public String id;

    @JsonProperty("idProvedor")
    public String idProvedor;

    @JsonProperty("id_category")
    public String idCategory;

    @JsonProperty("images")
    public List<ImagesDto> listImages;

    @JsonProperty("multimedia")
    public MultimediaDto multimedia;

    @JsonProperty("paquete")
    public PaqueteDto paquete;

    @JsonProperty("paymentMethods")
    public List<PaymentMethodsDto> listPaymentMethods;

    @JsonProperty("price")
    public PriceDto price;

    @JsonProperty("promotion")
    public PromotionDto promotion;

    @JsonProperty("provedor")
    public String provedor;

    @JsonProperty("sku")
    public String sku;

    @JsonProperty("status")
    public String status;

    @JsonProperty("stock")
    public String stock;

    @JsonProperty("summary")
    public String summary;

    @JsonProperty("tasaFinal")
    public double tasaFinal;

    @JsonProperty("title")
    public String title;

}
