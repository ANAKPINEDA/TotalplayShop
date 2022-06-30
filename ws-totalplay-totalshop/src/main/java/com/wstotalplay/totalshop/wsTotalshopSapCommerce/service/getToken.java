package com.wstotalplay.totalshop.wsTotalshopSapCommerce.service;


import com.wstotalplay.totalshop.config.RestTemplateConfig;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.dto.getToken.RefreshTokenDto;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity.MktConfigurationServiceEntity;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.repository.GetTokenInterface;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.ResultOperation;
import com.wstotalplay.totalshop.wsTotalshopSapCommerce.vo.getToken.ResponseGetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class getToken {

    @Autowired
    RestTemplateConfig restTemplate;


    @Autowired
    GetTokenInterface getTokenService;

    @Autowired
    private Environment env;


    public ResponseGetToken getToken(){
        ResponseGetToken resultResponseGetToken = new ResponseGetToken();

        try{
            MktConfigurationServiceEntity token = getTokenService.getToken();
            MktConfigurationServiceEntity refreshToken = getTokenService.getRefreshToken();
            if(token.getValue() != null && !token.getValue().isEmpty()){
                resultResponseGetToken = new ResponseGetToken(token.getValue(),"bearer","0","extended");
                resultResponseGetToken.setResultOperation(new ResultOperation("100","Servicio consumido correctamente"));
            }else if(refreshToken.getValue() != null && !refreshToken.getValue().isEmpty()){
                return getRefreshToken(refreshToken.getValue());
            }else{
                return resetToken();
            }
        }catch (Exception e){
            return resetToken();
        }

        return resultResponseGetToken;
    }

    public ResponseGetToken getRefreshToken(String value){
        ResponseGetToken resultResponseGetToken = new ResponseGetToken();
        String url = env.getProperty("ws.ecommerce.refreshToken") + value;
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            ResponseEntity<RefreshTokenDto> resultRefreshToken = restTemplate.restTemplate().exchange(url, HttpMethod.POST, entity, RefreshTokenDto.class);
            if(resultRefreshToken.getStatusCodeValue() == 200){
                if(resultRefreshToken.getBody().getAccessToken() != null && !resultRefreshToken.getBody().getAccessToken().isEmpty() && resultRefreshToken.getBody().getRefreshToken() != null && !resultRefreshToken.getBody().getRefreshToken().isEmpty()){
                    int updateAccessToken = getTokenService.updateAccessToken(resultRefreshToken.getBody().getAccessToken());
                    int updateRefreshToken = getTokenService.updateRefreshToken(resultRefreshToken.getBody().getRefreshToken());
                    if(updateAccessToken>0 && updateRefreshToken>0){
                       resultResponseGetToken = new ResponseGetToken(resultRefreshToken.getBody().getAccessToken(),"bearer","0","extended");
                       resultResponseGetToken.setResultOperation(new ResultOperation("100","Servicio consumido correctamente"));
                    }
                }else{
                    return resetToken();
                }
            }
        }catch (Exception e){
            resetToken();
        }
        return resultResponseGetToken;
    }

    public ResponseGetToken resetToken(){
        ResponseGetToken resultResponseGetToken = new ResponseGetToken();
        String url = env.getProperty("ws.ecommerce.resetToken");
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            ResponseEntity<RefreshTokenDto> resultResetToken = restTemplate.restTemplate().exchange(url, HttpMethod.POST, entity, RefreshTokenDto.class);
            if(resultResetToken.getStatusCodeValue() == 200){
                if(resultResetToken.getBody().getAccessToken() != null && !resultResetToken.getBody().getAccessToken().isEmpty() && resultResetToken.getBody().getTokenType() != null && !resultResetToken.getBody().getTokenType().isEmpty() && resultResetToken.getBody().getExpiresIn() != null && !resultResetToken.getBody().getExpiresIn().isEmpty()) {
                    int updateAccessToken = getTokenService.updateAccessToken(resultResetToken.getBody().getAccessToken());
                    int updateRefreshToken = getTokenService.updateRefreshToken(resultResetToken.getBody().getRefreshToken());
                    if(updateAccessToken>0 && updateRefreshToken>0){
                        resultResponseGetToken = new ResponseGetToken(resultResetToken.getBody().getAccessToken(),resultResetToken.getBody().getTokenType(),resultResetToken.getBody().getScope(),resultResetToken.getBody().getExpiresIn());
                        resultResponseGetToken.setResultOperation(new ResultOperation("100","Servicio consumido correctamente"));
                    }
                }else{
                    resultResponseGetToken = new ResponseGetToken("","","","");
                    resultResponseGetToken.setResultOperation(new ResultOperation("100","Error: No se obtuvo el Token, favor de revisar con el administrador"));
                }
            }
        }catch (Exception e){
        }
        return resultResponseGetToken;
    }
}
