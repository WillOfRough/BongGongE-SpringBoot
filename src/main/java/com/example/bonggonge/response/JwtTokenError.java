package com.example.bonggonge.response;


import com.example.bonggonge.config.jwt.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenError {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Map<String,String> jwtErrorCheck(HttpServletRequest request){
        Map<String,String> result = new HashMap<>();
        String requestTokenHeader = request.getHeader("token");
        if (requestTokenHeader != null) {
            try {
                jwtTokenUtil.getUsernameFromToken(requestTokenHeader);
                jwtTokenUtil.getEmailFromToken(requestTokenHeader);
                return null;
            } catch (IllegalArgumentException e) {
                result.put("detail","Unable to get JWT Token");
                result.put("code","auth-002");
            } catch (ExpiredJwtException e) {
                result.put("detail","JWT Token has expired");
                result.put("code","auth-001");
            }catch (Exception e){
                result.put("detail","Invalid JWT token");
                result.put("code","auth-003");
            }
        } else {
            result.put("detail","JWT token does not begin with Bearer String");
            result.put("code","auth-004");
        }
        return result;
    }

}
