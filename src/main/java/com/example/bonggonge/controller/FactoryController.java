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
    public Map<String, Object> factoryEdit(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> tokenError = jwtTokenError.jwtErrorCheck(request);
        if (tokenError == null) {
            Long userNo = jwtTokenUtil.getUsernoFromToken(request.getHeader("token"));
            if (params.containsKey("factoryName") && params.containsKey("latitude")
                    && params.containsKey("longitude")) {
                try {
                    result.put("result", factoryService.EditFactory(
                            userNo,
                            params.get("factoryName"),
                            params.get("detail"),
                            params.get("address"),
                            Double.parseDouble(params.get("latitude")),
                            Double.parseDouble(params.get("longitude")),
                            params.get("phone"),
                            params.get("UIimage")));
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


    @PostMapping("/factory/list")
    public Map<String, Object> factoryList(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> tokenError = jwtTokenError.jwtErrorCheck(request);
        if (tokenError == null) {
            Long userNo = jwtTokenUtil.getUsernoFromToken(request.getHeader("token"));
            if (params.containsKey("distance") && params.containsKey("longitude")
                    && params.containsKey("latitude")) {
                try {
                    result.put("result", factoryService.ListFactory(
                            Double.parseDouble(params.get("distance")),
                            Double.parseDouble(params.get("latitude")),
                            Double.parseDouble(params.get("longitude"))));
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
