package com.example.bonggonge.controller;

import com.example.bonggonge.config.jwt.JwtTokenUtil;
import com.example.bonggonge.response.ResponseError;
import com.example.bonggonge.service.sign.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ResponseError responseError;

    @PostMapping("/users/join")
    public Map<String,Object> signUp (@RequestBody Map<String, String> params) {
        Map<String,Object> result = new HashMap<>();
        if(params.containsKey("username") && params.containsKey("password")
                && params.containsKey("email") && params.containsKey("nickname")){
            try{
                result.put("result",loginService.joinUser(params.get("username"),params.get("password"),params.get("email")));
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

    @PostMapping("/users/auth")
    public  Map<String,Object> createAuthenticationToken(@RequestBody Map<String, String> params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        if(params.containsKey("username") && params.containsKey("password")){
            try{
                String token = loginService.login(params.get("username"),params.get("password"));
                if(token.equals("false")) {
                    result.put("error",responseError.makeResponseError("param-005"));
                    result.put("result",false);
                }
                else {
                    result.put("result", true);
                    result.put("token",token);
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

    @PostMapping("/users/join/check")
    public Map<String,Object> checkSignUp(@RequestBody Map<String, String> params){
        Map<String,Object> result = new HashMap<>();
        if(params.containsKey("username")){
            try{
                if(loginService.checkId(params.get("username"))){
                    result.put("error",responseError.makeResponseError("param-006"));
                    result.put("result",false);
                }else{
                    result.put("result",true);
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
