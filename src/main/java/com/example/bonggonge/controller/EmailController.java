package com.example.bonggonge.controller;

import com.example.bonggonge.response.ResponseError;
import com.example.bonggonge.service.sign.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ResponseError responseError;

    @PostMapping("/email/send")                                                  //이메일 보내기
    public Map<String,Object> sendEmail (@RequestBody Map<String, String> params) {
        Map<String,Object> result = new HashMap<>();
        if(params.containsKey("email")){
            try{
                result.put("result",emailService.sendEmail(params.get("email")));
            } catch (Exception e){
                e.printStackTrace();
                result.put("error",responseError.makeResponseError("server-001"));
                result.put("result",false);
            }
        }else{
            result.put("error",responseError.makeResponseError("param-003"));
            result.put("result",false);
        }
        return result;
    }

    @PostMapping("/email/auth")                                                          //이메일 맞는지 인증
    public Map<String,Object> authEmail(@RequestBody Map<String, String> params){
        Map<String,Object> result = new HashMap<>();
        if(params.containsKey("email") && params.containsKey("auth")){
            try{
                boolean answer = emailService.authEmail(params.get("email"),params.get("auth"));
                result.put("result",answer);
                if(!answer){
                    result.put("error",responseError.makeResponseError("param-004"));
                    result.put("result",false);
                }

            } catch (Exception e){
                result.put("error",responseError.makeResponseError("server-001"));
                result.put("result",false);
            }
        }else{
            result.put("error",responseError.makeResponseError("param-003"));
            result.put("result",false);
        }
        return result;
    }
}
