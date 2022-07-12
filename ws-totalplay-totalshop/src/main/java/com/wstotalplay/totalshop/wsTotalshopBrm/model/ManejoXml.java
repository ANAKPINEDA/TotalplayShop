package com.wstotalplay.totalshop.wsTotalshopBrm.model;

import java.util.List;

public class ManejoXml {

    public String inputXml(List<Args> listArgs, String flags, String poid, String template, String opCodeName, List<Result> listResult){
        String args = "";
        String results = "";
        if(listArgs.size() > 0){
            for(int index = 0; index < listArgs.size(); index++){
                    if(listArgs.get(index).getElem().isEmpty()){
                        args += "<ARGS elem =\""+ (index+1) +"\"" + "> <"+ listArgs.get(index).getEtiqueta()+">"+listArgs.get(index).getValor()+"</"+listArgs.get(index).getEtiqueta()+"></ARGS>" ;
                    }else{
                        args += "<ARGS elem =\""+ (index+1) +"\"" + "><"+listArgs.get(index).getEtiqueta() + " elem=\""+ listArgs.get(index).getElem()+"\">" + listArgs.get(index).getValor() +"</" + listArgs.get(index).getEtiqueta()+"></ARGS>";
                    }
            }
        }else{
            args += "<ARGS> </ARGS>";
        }
        if(listResult.size()>0){
            for(int index = 0; index < listResult.size(); index++){
                if(listResult.get(index).getElem().isEmpty()){
                    results +="<"+ listResult.get(index).getEtiqueta()+">"+listResult.get(index).getValor()+"</"+listResult.get(index).getEtiqueta()+">" ;
                }else{
                    results += "<" + listResult.get(index).getEtiqueta() + " elem=\""+ listResult.get(index).getElem()+"\">" + listResult.get(index).getValor() +"</" + listResult.get(index).getEtiqueta()+">";
                }
            }
        }
        String inputXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hac=\"http://hacks.tpe.org/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<hac:ExecuteXmlLoad>" +
                "<xmlInputFlist>" +
                "<![CDATA[<PCM_OP_SEARCH_inputFlist xmlns=\"http://xmlns.oracle.com/BRM/schemas/BusinessOpcodes\">" +
                 args+
                "<FLAGS>"+flags+"</FLAGS>" +
                "<POID>"+poid+"</POID>" +
                "<RESULTS>"+results+"</RESULTS>" +
                "<TEMPLATE>"+template+" </TEMPLATE>" +
                "</PCM_OP_SEARCH_inputFlist>]]>" +
                "</xmlInputFlist>" +
                "<opcopdeName>"+opCodeName+"</opcopdeName>" +
                "</hac:ExecuteXmlLoad>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
        return inputXml;
    }
}
