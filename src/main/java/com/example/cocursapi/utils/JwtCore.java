package com.example.cocursapi.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtCore {

    @Value("${testing.app.secret}")
    private String secret;

    @Value("${testing.app.lifetime}")
    private int lifetime;

    public String generationToken(Authentication authentication){
        UserDetailsImplementation userDetails  = (UserDetailsImplementation) authentication.getPrincipal();
        return Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+lifetime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String getNameFromJwt(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token) {
        try {
            // Parse the token and verify the signature
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

            // Check token expiration
            if (claims.getExpiration().before(new Date())) {
                return false;
            }

            // You can perform other checks here (issuer, audience, custom claims)

            return true;
        } catch (Exception e) {
            // Token validation failed
            return false;
        }
    }

}