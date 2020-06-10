package com.example.bonggonge.controller;

import com.example.bonggonge.config.jwt.JwtTokenUtil;
import com.example.bonggonge.response.JwtTokenError;
import com.example.bonggonge.response.ResponseError;
import com.example.bonggonge.service.board.FactoryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@Validated
public class FactoryController {

    private final ResponseError responseError;
    private final FactoryService factoryService;
    private final JwtTokenError jwtTokenError;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/factory/edit")
    public Map<String, Object> signUp(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> tokenError = jwtTokenError.jwtErrorCheck(request);
        if (tokenError == null) {
            Long userNo = jwtTokenUtil.getUsernoFromToken(request.getHeader("token"));
            if (params.containsKey("factoryName") && params.containsKey("factoryLat")
                    && params.containsKey("factoryLng")) {
                try {
                    result.put("result", factoryService.EditFactory(userNo,params.get("factoryName"),
                            Integer.parseInt(params.get("factoryLat")), Integer.parseInt(params.get("factoryLng"))));
                } catch (Exception e) {
                    result.put("error", responseError.makeResponseError("server-001"));
                    result.put("result", false);
                }
            } else {
                result.put("error", responseError.makeResponseError("param-003"));
                result.put("result", false);
            }
        }
        else{
            result.put("result",false);
            result.put("error",tokenError);
        }
        return result;
    }
}
