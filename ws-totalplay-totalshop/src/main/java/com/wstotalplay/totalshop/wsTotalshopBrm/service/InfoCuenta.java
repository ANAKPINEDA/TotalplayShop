package com.wstotalplay.totalshop.wsTotalshopBrm.service;

import com.wstotalplay.totalshop.config.RestTemplateConfig;
import com.wstotalplay.totalshop.wsTotalshopBrm.model.Args;
import com.wstotalplay.totalshop.wsTotalshopBrm.model.ManejoXml;
import com.wstotalplay.totalshop.wsTotalshopBrm.model.Result;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.ResultOperation;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta.AcountDescVo;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta.AddressVo;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta.PhoneVo;
import com.wstotalplay.totalshop.wsTotalshopBrm.vo.infoCuenta.ResponseInfoCuenta;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoCuenta {

    @Autowired
    private Environment env;

    @Autowired
    RestTemplateConfig restTemplate;


    public ResponseInfoCuenta getInfoCuenta(String cuenta){
        ResponseInfoCuenta responseInfoCuenta = new ResponseInfoCuenta();
        AcountDescVo acountDescVo = new AcountDescVo();
        JSONObject jsonInfoBrm = new JSONObject();
        JSONObject jsonProfile = new JSONObject();
        JSONObject jsonGetSegment = new JSONObject();
        JSONObject jsonGetBehaibor = new JSONObject();
        JSONObject jsonGetContrato = new JSONObject();
        JSONObject jsonGetDn = new JSONObject();
        try{
            var resultInfoCuenta = getinfoBrm(cuenta);
            if(resultInfoCuenta.toString().contains("brm:RESULTS") && resultInfoCuenta.toString().contains("brm:POID")){
                jsonInfoBrm = resultInfoCuenta.getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns0:ExecuteXmlLoadResponse").getJSONObject("return").getJSONObject("brm:PCM_OP_SEARCH_outputFlist").getJSONObject("brm:RESULTS");
                jsonProfile = getProfile(jsonInfoBrm.get("brm:POID").toString()).getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns0:ExecuteXmlLoadResponse").getJSONObject("return").getJSONObject("brm:PCM_OP_SEARCH_outputFlist").getJSONObject("brm:RESULTS");
                jsonGetSegment = getSegment(jsonInfoBrm.get("brm:CUSTOMER_SEGMENT_LIST").toString()).getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns0:ExecuteXmlLoadResponse").getJSONObject("return").getJSONObject("brm:PCM_OP_SEARCH_outputFlist").getJSONObject("brm:RESULTS");
                jsonGetBehaibor = getBehaibor(jsonInfoBrm.get("brm:POID").toString()).getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns0:ExecuteXmlLoadResponse").getJSONObject("return");
                acountDescVo.setAcount(jsonInfoBrm.get("brm:ACCOUNT_NO").toString());
                acountDescVo.setName(jsonInfoBrm.get("brm:AAC_VENDOR").toString());
                acountDescVo.setStatus(jsonInfoBrm.get("brm:STATUS").toString());
                acountDescVo.setTipoRelojCobranza(jsonInfoBrm.get("brm:ACCESS_CODE1").toString());
                if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().contains("TFE")){
                    acountDescVo.setBusinessUnit("TFE");
                }else{
                    acountDescVo.setBusinessUnit(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(1,2));
                }
                if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().contains("TFE")){
                    acountDescVo.setBusinessDescription("TFE");
                }else{
                    if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(1,2).toString().equals("4")){
                        acountDescVo.setBusinessDescription("Totalplay");
                    }
                    if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(1,2).toString().equals("3") || jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(1,2).toString().equals("5")){
                        acountDescVo.setBusinessDescription("Enlace TPE S.A. de C.V");
                    }
                }
                acountDescVo.setTaxRegime(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(jsonInfoBrm.get("brm:GL_SEGMENT").toString().length()-1));
                if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(jsonInfoBrm.get("brm:GL_SEGMENT").toString().length()-1).equals("1")){
                    acountDescVo.setTaxRegimeDescription("Fisica");
                }
                if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(jsonInfoBrm.get("brm:GL_SEGMENT").toString().length()-1).equals("2")){
                    acountDescVo.setTaxRegimeDescription("Moral");
                }
                if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(jsonInfoBrm.get("brm:GL_SEGMENT").toString().length()-1).equals("3")){
                    acountDescVo.setTaxRegimeDescription("Fisica con actividad empresarial");
                }
                acountDescVo.setContractType("");
                acountDescVo.setCurrency(jsonInfoBrm.get("brm:CURRENCY").toString());
                if(jsonInfoBrm.get("brm:CURRENCY").toString().equals("484")){
                    acountDescVo.setCurrencyDescription("MXN");
                }else{
                    acountDescVo.setCurrencyDescription("");
                }
                acountDescVo.setCluster(jsonProfile.getJSONObject("brm:TPL_FLD_SUBSTRUCT").get("brm:TPL_FLD_CLSTR").toString());
                acountDescVo.setSegmentation(jsonInfoBrm.get("brm:CUSTOMER_SEGMENT_LIST").toString());
                acountDescVo.setDescSegmentation(jsonGetSegment.getJSONObject("brm:CUSTOMER_SEGMENTS").get("brm:DESCR").toString());
                if(jsonGetBehaibor.getJSONObject("brm:PCM_OP_SEARCH_outputFlist").toString().contains("brm:RESULTS")){
                    acountDescVo.setBehaivorScore(jsonGetBehaibor.getJSONObject("brm:PCM_OP_SEARCH_outputFlist").getJSONArray("brm:RESULTS").getJSONObject(0).get("brm:TOTAL_RECORDS").toString());
                }else {
                    acountDescVo.setBehaivorScore("");
                }
                acountDescVo.setDn("");
                acountDescVo.setCreationDateAcount(jsonInfoBrm.get("brm:CREATED_T").toString());
                acountDescVo.setIdPackage(jsonInfoBrm.get("brm:AAC_PROMO_CODE").toString());
                acountDescVo.setPackageDescription(jsonInfoBrm.get("brm:AAC_PACKAGE").toString());
                acountDescVo.setRent(jsonInfoBrm.get("brm:AAC_ACCESS").toString());
                acountDescVo.setPrepaid(jsonProfile.getJSONObject("brm:TPL_FLD_SUBSTRUCT").get("brm:ACCOUNT_TYPE").toString());
                List<AddressVo> listAdress = new ArrayList<>();
                for(int index = 0; index < jsonInfoBrm.getJSONArray("brm:NAMEINFO").length(); index++){
                    AddressVo addressVo = new AddressVo();
                    addressVo.setId(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:CONTACT_TYPE").toString());
                    if(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:CONTACT_TYPE").toString().equals("1")){
                        addressVo.setIdDescription("Fiscal");
                    }else if(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:CONTACT_TYPE").toString().equals("2")){
                        addressVo.setIdDescription("Instalacion");
                    }else{
                        addressVo.setIdDescription("Envio");
                    }
                    addressVo.setFirstName(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:FIRST_NAME").toString());
                    addressVo.setMiddleName(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:MIDDLE_NAME").toString());
                    addressVo.setLastName(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:LAST_NAME").toString());
                    addressVo.setConpany(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:COMPANY").toString());
                    addressVo.setTitle(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:TITLE").toString());
                    addressVo.setGender(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:TPL_FLD_GENDER").toString());
                    addressVo.setBirthday(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:TPL_FLD_BIRTHDAY").toString());
                    String array[] = jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:ADDRESS").toString().split("\\|\\|");
                    addressVo.setStreet(array[0]);
                    addressVo.setAutdoorNum(array[1]);
                    addressVo.setCity(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:CITY").toString());
                    addressVo.setColony(array[2]);
                    addressVo.setDelegation(array[3]);
                    addressVo.setCountry(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:COUNTRY").toString());
                    addressVo.setEmail(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:EMAIL_ADDR").toString());
                    addressVo.setState(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:STATE").toString());
                    addressVo.setZip(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).get("brm:ZIP").toString());
                    List<PhoneVo> listPhones = new ArrayList<>();
                    for(int subIndex = 0; subIndex < jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).getJSONArray("brm:PHONES").length(); subIndex++){
                        PhoneVo phoneVo = new PhoneVo();
                        phoneVo.setPhone(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).getJSONArray("brm:PHONES").getJSONObject(subIndex).get("brm:PHONE").toString());
                        phoneVo.setType(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).getJSONArray("brm:PHONES").getJSONObject(subIndex).get("brm:TYPE").toString());
                        if(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).getJSONArray("brm:PHONES").getJSONObject(subIndex).get("brm:TYPE").toString().equals("1")){
                            phoneVo.setDescription("Casa");
                        }else if(jsonInfoBrm.getJSONArray("brm:NAMEINFO").getJSONObject(index).getJSONArray("brm:PHONES").getJSONObject(subIndex).get("brm:TYPE").toString().equals("2")){
                            phoneVo.setDescription("Trabajo");
                        }else {
                            phoneVo.setDescription("Celular");
                        }
                        listPhones.add(phoneVo);
                    }
                    addressVo.setListPhones(listPhones);
                    listAdress.add(addressVo);
                }
                if(jsonProfile.getJSONObject("brm:TPL_FLD_SUBSTRUCT").get("brm:TPL_FLD_RAZ_SOCIAL").toString().equals("4")){
                    acountDescVo.setContractType("Forward");
                }else{
                    jsonGetContrato = getContrato(jsonInfoBrm.get("brm:POID").toString());
                    if(jsonGetContrato.toString().contains("brm:RESULTS")){
                        acountDescVo.setContractType("Forward");
                    }else{
                        acountDescVo.setContractType("Arrier");
                    }
                }
                if(jsonInfoBrm.get("brm:GL_SEGMENT").toString().substring(1,2).equals("4")){
                    jsonGetDn = getDn(jsonInfoBrm.get("brm:POID").toString());
                    if(jsonGetDn.toString().contains("brm:RESULTS")){
                        acountDescVo.setDn(jsonGetDn.getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns0:ExecuteXmlLoadResponse")
                                .getJSONObject("return").getJSONObject("brm:PCM_OP_SEARCH_outputFlist")
                                .getJSONObject("brm:RESULTS").get("brm:LOGIN").toString());
                    }
                }
                acountDescVo.setListAddress(listAdress);
                responseInfoCuenta.setResultOperation(new ResultOperation("100","Servicio consumido correctamente"));
                responseInfoCuenta.setAcountDescVo(acountDescVo);
            }else{
                responseInfoCuenta.setResultOperation(new ResultOperation("236","No se encontro información de la cuenta"));
                return responseInfoCuenta;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error General");
            responseInfoCuenta.setResultOperation(new ResultOperation("101","Por el momento no es posible realizar esta operación. ¡Inténtalo más tarde!"));
        }
        return responseInfoCuenta;
    }

    public JSONObject getinfoBrm(String cuenta){
        JSONObject json = new JSONObject();
        List<Args> argsList = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();
        String url = env.getProperty("ws.brm.apiDirect");
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type", "text/xml");
            argsList.add(new Args("ACCOUNT_NO","",cuenta));
            String xmlInput = new ManejoXml().inputXml(argsList,"256","0.0.0.1 /search -1 0","select X from /account where F1=V1 ","PCM_OP_SEARCH", resultList);
            HttpEntity<String> request = new HttpEntity(xmlInput,headers);
            ResponseEntity<String> response =  restTemplate.restTemplate().exchange(url, HttpMethod.POST, request, String.class);
            if(response.getStatusCodeValue() == 200){
                String xml = response.getBody().replaceAll("&lt;","<")
                        .replaceAll("<\\?xml version='1.0' encoding='UTF-8'\\?>","")
                        .replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>","")
                        .replaceAll("&gt;",">");
                json = XML.toJSONObject(xml);
                return json;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error en la petición OpSearch getInfoCuentaBrm");
        }
        return json;
    }

    public JSONObject getProfile(String poid){
        JSONObject json = new JSONObject();
        List<Args> argsList = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();
        String url = env.getProperty("ws.brm.apiDirect");
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type", "text/xml");
            argsList.add(new Args("ACCOUNT_OBJ","",poid));
            String xmlInput = new ManejoXml().inputXml(argsList,"256","0.0.0.1 /search -1 0","select X from /profile where F1 = V1 and poid_type ='/profile/totalplay/acct'","PCM_OP_SEARCH", resultList);
            HttpEntity<String> request = new HttpEntity(xmlInput,headers);
            ResponseEntity<String> response =  restTemplate.restTemplate().exchange(url, HttpMethod.POST, request, String.class);
            if(response.getStatusCodeValue() == 200){
                String xml = response.getBody().replaceAll("&lt;","<")
                        .replaceAll("<\\?xml version='1\\.0' encoding='UTF-8'\\?>","")
                        .replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>","")
                        .replaceAll("&gt;",">");
                json = XML.toJSONObject(xml);
                return json;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error en la petición OpSearch getProfile");
        }
        return json;
    }

    public JSONObject getSegment(String customerSegments){
        JSONObject json = new JSONObject();
        List<Args> argsList = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();
        String url = env.getProperty("ws.brm.apiDirect");
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type", "text/xml");
            argsList.add(new Args("POID","","/config/customer_segment"));
            resultList.add(new Result("CUSTOMER_SEGMENTS",customerSegments,""));
            String xmlInput = new ManejoXml().inputXml(argsList,"256","0.0.0.1 /search -1 0","select X from /config where F1 = V1","PCM_OP_SEARCH", resultList);
            HttpEntity<String> request = new HttpEntity(xmlInput,headers);
            ResponseEntity<String> response =  restTemplate.restTemplate().exchange(url, HttpMethod.POST, request, String.class);
            if(response.getStatusCodeValue() == 200){
                String xml = response.getBody().replaceAll("&lt;","<")
                        .replaceAll("<\\?xml version='1\\.0' encoding='UTF-8'\\?>","")
                       .replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>","")
                        .replaceAll("&gt;",">");
                json = XML.toJSONObject(xml);
                return json;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error en la petición OpSearch getSegment");
        }
        return json;
    }

    public JSONObject getBehaibor(String poid){
        JSONObject json = new JSONObject();
        List<Args> argsList = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();
        String url = env.getProperty("ws.brm.apiDirect");
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type", "text/xml");
            argsList.add(new Args("ACCOUNT_OBJ","",poid));
            String xmlInput = new ManejoXml().inputXml(argsList,"256","0.0.0.1 /search -1 0","select X from /behavior where F1=V1 order by EFFECTIVE_T desc","PCM_OP_SEARCH", resultList);
            HttpEntity<String> request = new HttpEntity(xmlInput,headers);
            ResponseEntity<String> response =  restTemplate.restTemplate().exchange(url, HttpMethod.POST, request, String.class);
            if(response.getStatusCodeValue() == 200){
                String xml = response.getBody().replaceAll("&lt;","<")
                        .replaceAll("<\\?xml version='1\\.0' encoding='UTF-8'\\?>","")
                        .replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>","")
                        .replaceAll("&gt;",">");
                json = XML.toJSONObject(xml);
                return json;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error en la petición OpSearch getBehaibor");
        }
        return json;
    }

    public JSONObject getContrato(String poid){
        JSONObject json = new JSONObject();
        List<Args> argsList = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();
        String url = env.getProperty("ws.brm.apiDirect");
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type", "text/xml");
            argsList.add(new Args("ACCOUNT_OBJ","",poid));
            argsList.add(new Args("PRODUCT_OBJ","","0.0.0.1 /product 6211496358"));
            argsList.add(new Args("STATUS","","1"));
            String xmlInput = new ManejoXml().inputXml(argsList,"256","0.0.0.1 /search -1 0","select X from /purchased_product where F1=V1 and F2=V2 and F3 =V3","PCM_OP_SEARCH", resultList);
            System.out.println("getContrato: " + xmlInput);
            HttpEntity<String> request = new HttpEntity(xmlInput,headers);
            ResponseEntity<String> response =  restTemplate.restTemplate().exchange(url, HttpMethod.POST, request, String.class);
            if(response.getStatusCodeValue() == 200){
                String xml = response.getBody().replaceAll("&lt;","<")
                        .replaceAll("<\\?xml version='1\\.0' encoding='UTF-8'\\?>","")
                        .replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>","")
                        .replaceAll("&gt;",">");
                json = XML.toJSONObject(xml);
                return json;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error en la petición OpSearch getContrato");
        }
        return  json;
    }

    public JSONObject getDn(String poid){
        JSONObject json = new JSONObject();
        List<Args> argsList = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();
        String url = env.getProperty("ws.brm.apiDirect");
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type", "text/xml");
            argsList.add(new Args("ACCOUNT_OBJ","",poid));
            argsList.add(new Args("STATUS","","10100"));
            resultList.add(new Result("ACCOUNT_OBJ","",""));
            resultList.add(new Result("POID","",""));
            resultList.add(new Result("LOGIN","",""));
            String xmlInput = new ManejoXml().inputXml(argsList,"256","0.0.0.1 /search -1 0","select X from /service where F1=V1 and F2=V2 and poid_type ='/service/totalplay/telephony'","PCM_OP_SEARCH", resultList);
            HttpEntity<String> request = new HttpEntity(xmlInput,headers);
            ResponseEntity<String> response =  restTemplate.restTemplate().exchange(url, HttpMethod.POST, request, String.class);
            if(response.getStatusCodeValue() == 200){
                String xml = response.getBody().replaceAll("&lt;","<")
                        .replaceAll("<\\?xml version='1\\.0' encoding='UTF-8'\\?>","")
                        .replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>","")
                        .replaceAll("&gt;",">");
                json = XML.toJSONObject(xml);
                return json;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error en la petición OpSearch getDn");
        }
        return json;
    }
}
