package com.example.bonggong.config.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

//        if (request.getMethod().equals("GET") || request.getMethod().equals("POST")) {
//            chain.doFilter(new RequestWrapperForXssFiltering(request), response);
//        } else {
//            chain.doFilter(request, response);
//        }
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Headers","authorization,content-type,token");
        response.addHeader("Access-Control-Allow-Headers", "token");


//        final String requestTokenHeader = request.getHeader("token");
//        if (requestTokenHeader != null) {
////            jwtToken = requestTokenHeader;
//            try {
//                System.out.println(jwtTokenUtil.getUsernameFromToken(requestTokenHeader));
//                System.out.println(jwtTokenUtil.getEmailFromToken(requestTokenHeader));
//            } catch (IllegalArgumentException e) {
//                System.out.println("Unable to get JWT Token");
//            } catch (ExpiredJwtException e) {
//                System.out.println("JWT Token has expired");
//            }catch (Exception e){
//                System.out.println("JWT Error");
//            }
//        } else {
//            logger.warn("JWT Token does not begin with Bearer String");
//        }
        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request, response);
            return;
        }
    }
}