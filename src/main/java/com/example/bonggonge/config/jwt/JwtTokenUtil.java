package com.example.bonggonge.config.jwt;

import com.example.bonggonge.dto.UserJwtDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 365 * 24 * 60 * 60;

    private String secret="sam572";
    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        return (String) claims.getBody().get("username");
    }
    public String getEmailFromToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        return (String) claims.getBody().get("email");
    }
    public Long getUsernoFromToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        return Long.valueOf(String.valueOf(claims.getBody().get("userNo")));
    }
    /*
    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
     */


    //generate token for user
    public String generateToken(UserJwtDto userJwtDto) {
        return Jwts.builder()
                .claim("userNo",userJwtDto.getNo())
                .claim("email",userJwtDto.getEmail())
                .claim("username",userJwtDto.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}