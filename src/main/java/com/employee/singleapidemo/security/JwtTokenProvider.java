package com.employee.singleapidemo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    //Generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //Get username from Jwt token

    public String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return username;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key())
                    .build()
                    .parse(token);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
//        catch (MalformedJwtException ex){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
//        } catch (ExpiredJwtException ex) {
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
//        } catch (UnsupportedJwtException ex){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
//        } catch (IllegalArgumentException ex){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
//        }
    }
}

