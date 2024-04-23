package com.store.api.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String JWT_SECRET= "jxgEQe.XHuPq8VdbyYFNkAN.dudQ0903YUn4";


    private Claims getAllClaimsFromToken(String token) {
        final var key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final var claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    private Date getExpirationDateFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }


    private Boolean isTokenExpired(String token) {
        final var expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }


    public String getUsernameFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getSubject);
    }

    public String getClaimFromToken(String token, String claimKey) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(claimKey, String.class);
    }
    public String generateToken(UserDetails userDetails) {
        final var authority = userDetails.getAuthorities().iterator().next().getAuthority();
        final Map<String, Object> claims = new HashMap<>();
        claims.put("role", authority);
        claims.put("email", userDetails.getUsername());
        return this.getToken(claims, userDetails.getUsername());
    }
    public String getRoleFromToken(String token) {
        return getClaimFromToken(token, "role");
    }
    
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, "email");
    }



    private String getToken(Map<String, Object> claims, String subject) {
        final var key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key)
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final var emailFromUserDetails  = userDetails.getUsername();
        final var emailFromJWT  = this.getEmailFromToken(token);
    
        return (emailFromUserDetails.equals(emailFromJWT)) && !this.isTokenExpired(token);
    }
}
