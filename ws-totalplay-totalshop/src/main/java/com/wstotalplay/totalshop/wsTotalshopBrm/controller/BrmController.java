package com.wstotalplay.totalshop.wsTotalshopBrm.controller;


import com.wstotalplay.totalshop.wsTotalshopBrm.service.InfoCuenta;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.ResultOperation;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta.RequestGetInfoCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TotalplayShop")
public class BrmController {

    @Autowired
    InfoCuenta infoCuenta;

    @PostMapping(value = "/Brm/infoCuenta")
    public ResponseEntity<?> getToken(@RequestBody RequestGetInfoCuenta request){
        var result = infoCuenta.getInfoCuenta(request.getAccountNo());
        if(result.getResultOperation().getResult() == "100"){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.ok(new ResultOperation(result.getResultOperation().getResult(),result.getResultOperation().getResultDescription()));
        }

    }
}
