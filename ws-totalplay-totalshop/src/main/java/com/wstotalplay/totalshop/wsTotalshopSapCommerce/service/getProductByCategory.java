package com.wstotalplay.totalshop.wsTotalshopSapCommerce.service;


import com.wstotalplay.totalshop.config.RestTemplateConfig;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductDetail.ProductDetailDto;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getProductsByCategory.ProductsByCategoryDto;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity.BannerByCategoryEntity;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity.ColorEntity;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.repository.BannerByCategoryInterface;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.repository.ColorInterface;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.ResultOperation;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class getProductByCategory {

    @Autowired
    RestTemplateConfig restTemplate;

    @Autowired
    private Environment env;

    @Autowired
    getToken tokenImpl;

    @Autowired
    BannerByCategoryInterface BannerByCategoryInterface;

    @Autowired
    ColorInterface colorInterface;


    public ResponseProductByCategory productsByCategory(String idCategory, String page, String pageSize){
        ResponseProductByCategory responseProductByCategory = new ResponseProductByCategory();
        ProductDetailDto productDetail = null;
        ProductsByCategoryDto productsByCategory = null;
        boolean category = false;
        try{
            BannerByCategoryEntity banner = getBanner(idCategory);
            if(banner == null){
              responseProductByCategory.setResultOperation(new ResultOperation("100","Servicio consumido correctamente, No hay productos para mostrar"));
              return responseProductByCategory;
            }
            if(banner.getId_commerce().length() >= 4){
                productDetail = getProductDetail(banner.getId_commerce(),tokenImpl.getToken().getAccessToken());
                if(productDetail == null){
                    responseProductByCategory.setResultOperation(new ResultOperation("168","Por el momento no es posible realizar esta operación. ¡Inténtalo más tarde"));
                    return responseProductByCategory;
                }
            }else{
                category = true;
            }
            ColorEntity color = getColor("color.properti");
            productsByCategory = getProductsByCategory(idCategory, page,pageSize);
            if(productsByCategory.getListProducts().size() > 0){
                List<ProductsVo> listProductsVo = new ArrayList<>();
                for(int index = 0; index < productsByCategory.getListProducts().size(); index++){
                    Bannervo bannervo = new Bannervo();
                    ProductsVo productsVo = new ProductsVo();
                    if(index == 0){
                        if(!category){
                            bannervo.setType("product");
                            bannervo.setContent(new ContentVo(banner.getId_commerce(), productDetail.getPrice().getPriceWithDiscountRounded(),
                                    productDetail.getPrice().getAmountRounded(), productDetail.getBrand(), productDetail.getTitle(), banner.getDescription(), "", banner.getHref(),
                                    new PromotionVo(productDetail.getPromotion().getDiscountRounded(),
                                            formatDate(productDetail.getPromotion().getDateEnd()),
                                            productDetail.getPromotion().getDisccountPriceRounded(),
                                            productDetail.getPromotion().getQuantityDisccountRounded()),
                                    productDetail.getSummary()));
                            productsVo.setId(productsByCategory.getListProducts().get(index).getId());
                            productsVo.setName(productsByCategory.getListProducts().get(index).getTitle());
                            productsVo.setMedia(new MediaVo(List.of(productsByCategory.getListProducts().get(index).getMultimedia().getImg().split(","))));
                            productsVo.setPriceVo(new PriceVo(productsByCategory.getListProducts().get(index).getPrice().getPriceWithDiscountRounded(), color.getMcs_text(), productsByCategory.getListProducts().get(index).getPrice().getAmountRounded()));
                            productsVo.setPromotion(new PromotionVo(productsByCategory.getListProducts().get(index).getPromotion().getDiscountRounded(),
                                    formatDate(productsByCategory.getListProducts().get(index).getPromotion().getDateEnd()),
                                    productsByCategory.getListProducts().get(index).getPromotion().getDisccountPriceRounded(),
                                    productsByCategory.getListProducts().get(index).getPromotion().getQuantityDisccountRounded()));
                            productsVo.setSummary(productsByCategory.getListProducts().get(index).getSummary());
                            productsVo.setTotalplay(new TotalplayVo(productsByCategory.getListProducts().get(index).getTotalplay().getMin(), productsByCategory.getListProducts().get(index).getTotalplay().getMonth()));
                            listProductsVo.add(productsVo);
                        }else{
                            bannervo.setType("category");
                            bannervo.setContent(new ContentVo(banner.getId_commerce(), "", "", "", "", banner.getDescription(), "", banner.getHref(),
                                    new PromotionVo("","","",""), ""));
                            productsVo.setMedia(new MediaVo(List.of(productsByCategory.getListProducts().get(index).getMultimedia().getImg().split(","))));
                            productsVo.setId(productsByCategory.getListProducts().get(index).getId());
                            productsVo.setName(productsByCategory.getListProducts().get(index).getTitle());
                            productsVo.setSummary(productsByCategory.getListProducts().get(index).getSummary());
                            productsVo.setPriceVo(new PriceVo(productsByCategory.getListProducts().get(index).getPrice().getPriceWithDiscountRounded(), color.getMcs_text(), productsByCategory.getListProducts().get(index).getPrice().getAmountRounded()));
                            productsVo.setPromotion(new PromotionVo(productsByCategory.getListProducts().get(index).getPromotion().getDiscountRounded(),
                                    formatDate(productsByCategory.getListProducts().get(index).getPromotion().getDateEnd()),
                                    productsByCategory.getListProducts().get(index).getPromotion().getDisccountPriceRounded(),
                                    productsByCategory.getListProducts().get(index).getPromotion().getQuantityDisccountRounded()));
                            productsVo.setTotalplay(new TotalplayVo("",""));
                            listProductsVo.add(productsVo);
                        }
                        responseProductByCategory.setBanner(bannervo);
                    }else{
                        productsVo.setId(productsByCategory.getListProducts().get(index).getId());
                        productsVo.setName(productsByCategory.getListProducts().get(index).getTitle());
                        productsVo.setSummary(productsByCategory.getListProducts().get(index).getSummary());
                        productsVo.setPromotion(new PromotionVo(productsByCategory.getListProducts().get(index).getPromotion().getDiscountRounded(),
                                formatDate(productsByCategory.getListProducts().get(index).getPromotion().getDateEnd()),
                                productsByCategory.getListProducts().get(index).getPromotion().getDisccountPriceRounded(),
                                productsByCategory.getListProducts().get(index).getPromotion().getQuantityDisccountRounded()));
                        productsVo.setPriceVo(new PriceVo(productsByCategory.getListProducts().get(index).getPrice().getPriceWithDiscountRounded(),
                                color.getMcs_text(),
                                productsByCategory.getListProducts().get(index).getPrice().getAmountRounded()));
                        productsVo.setMedia(new MediaVo(List.of(productsByCategory.getListProducts().get(index).getMultimedia().getImg().split(","))));
                        productsVo.setTotalplay(new TotalplayVo(productsByCategory.getListProducts().get(index).getTotalplay().getMin(),
                                productsByCategory.getListProducts().get(index).getTotalplay().getMonth()));
                        listProductsVo.add(productsVo);
                    }
                }
                responseProductByCategory.setListProducts(listProductsVo);
                responseProductByCategory.setPage(Integer.parseInt(page));
                responseProductByCategory.setTotalPages(productsByCategory.getPagination().getTotalPages());
                responseProductByCategory.setResultOperation(new ResultOperation("100","Servicio Consumido Correctamente"));
            }else{
                responseProductByCategory.setResultOperation(new ResultOperation("100","Servicio consumido correctamente, No hay productos para mostrar"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error General");
            responseProductByCategory.setResultOperation(new ResultOperation("101","Por el momento no es posible realizar esta operación. ¡Inténtalo más tarde!"));
        }
        return responseProductByCategory;
    }


    public BannerByCategoryEntity getBanner(String idCategory){
        BannerByCategoryEntity banner = new BannerByCategoryEntity();
        try{
            banner = BannerByCategoryInterface.getBannerByCategory(idCategory);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error al consultar el banner");
        }
        return banner;
    }

    public ColorEntity getColor(String idText){
        ColorEntity color = new ColorEntity();
        try{
            color = colorInterface.getColor(idText);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error al obtener el color");
        }
        return color;
    }

    public ProductDetailDto getProductDetail(String idCommerce, String accessToken){
        ProductDetailDto productDetail = new ProductDetailDto();
        HttpHeaders headers = new HttpHeaders();
        try{
            String url = env.getProperty("ws.ecommerce.productDetail") + idCommerce;
            headers.set("Authorization", "Bearer " + accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            ResponseEntity<ProductDetailDto> result = restTemplate.restTemplate().exchange(url, HttpMethod.GET, entity, ProductDetailDto.class);
            if(result.getStatusCodeValue() == 200){
                productDetail = result.getBody();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error al consultar en getProductDetail hacia E-Commerce");
        }
        return productDetail;
    }

    public ProductsByCategoryDto getProductsByCategory(String idCategory, String page, String pageSize){
        ProductsByCategoryDto productsByCategory = new ProductsByCategoryDto();
        String url = env.getProperty("ws.ecommerce.productsByCategory");
        HttpHeaders headers = new HttpHeaders();
        try{
            url = url +idCategory+"&currentPage="+page+"&pageSize="+pageSize;
            headers.set("Authorization", "Bearer " + tokenImpl.getToken().accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            ResponseEntity<ProductsByCategoryDto> result = restTemplate.restTemplate().exchange(url, HttpMethod.GET, entity, ProductsByCategoryDto.class);
            if(result.getStatusCodeValue() == 200){
                productsByCategory = result.getBody();
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
            System.out.println("Error al consultar en getProductsByCategory hacia E-Commerce");
        }
        return productsByCategory;
    }

    public String formatDate(String date){
        String dateFormat = "";
        try{
            if(date != ""){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                dateFormat = sdf2.format(sdf.parse(date));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error al dar formato en la fecha");
        }
        return dateFormat;
    }

}
