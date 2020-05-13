package com.example.bonggonge.response;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseError {


    public Map<String,String> makeResponseError(String code){
        Map<String, String> result = new HashMap<>();
        result.put("code",code);
        String detail = "";
        switch (code){
            case "param-001":
                detail = "Parameter value is null";
                break;
            case "param-002":
                detail = "Invalid parameter(s)";
                break;
            case "param-003":
                detail = "Parameter name mismatch";
                break;
            case "param-004":
                detail = "Email auth error. check the value again";
                break;
            case "param-005":
                detail = "User auth error. check the value again";
                break;
            case "param-006":
                detail = "Already userd username";
                break;
            case "perm-001":
                detail = "Permission denied";
                break;
            case "server-001":
                detail = "Internal server error";
                break;
            default:
                detail = "Unknown error code. Check the ResponseError class.";
        }
        result.put("detail",detail);
        return result;
    }
}
