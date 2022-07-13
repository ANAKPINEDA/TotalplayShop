package com.wstotalplay.totalshop.wsTotalshopSapCommerce.controller;

import com.wstotalplay.totalshop.wsTotalshopBrm.vo.ResultOperation;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.service.getProductByCategory;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.service.getToken;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.Request;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getProductByCategory.RequestProductByCategory;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getToken.ResponseGetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/TotalplayShop")
public class CommerceController {

    @Autowired
    getToken getTokenImpl;


    @Autowired
    getProductByCategory productByCategory;


    @PostMapping(value = "/Commerce/getToken")
    public ResponseEntity<ResponseGetToken> getToken(@RequestBody Request request){
        return ResponseEntity.ok(getTokenImpl.getToken());
    }

    @PostMapping(value = "/Commerce/resetToken")
    public ResponseEntity<ResponseGetToken> resetToken(@RequestBody Request request){
        return ResponseEntity.ok(getTokenImpl.resetToken());
    }

    @PostMapping(value = "/Commerce/getProductByCategory")
    public ResponseEntity<?> getProductByCategory(@RequestBody RequestProductByCategory request){
        var result = productByCategory.productsByCategory(request.getIdCategory(),request.getPage(),request.getPageSize());
        if(result.getResultOperation().getResult() == "100"){
            if(result.getResultOperation().getResultDescription().equals("Servicio consumido correctamente, No hay productos para mostrar")){
                return ResponseEntity.ok(new ResultOperation("100","Servicio consumido correctamente, No hay productos para mostrar"));
            }
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.ok(new ResultOperation(result.getResultOperation().getResult(),result.getResultOperation().getResultDescription()));
        }
    }
}
