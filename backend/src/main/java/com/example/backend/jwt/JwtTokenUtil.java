package com.example.backend.jwt;

import com.example.backend.repository.EndUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.Serializable;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {
    private final String secretString = "thisisthesecretkey";
    byte[] secret = secretString.getBytes();
    private EndUserRepository endUserRepository;

    public JwtTokenUtil(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    public boolean checkLoggedIn(String token) {
        return checkUserInDB(getClaimFromToken(token, Claims::getSubject));
    }

    public boolean checkUserLessor(String token){
        return checkLessorInDB(getClaimFromToken(token, Claims::getSubject));
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean checkUserInDB(String id){
        return endUserRepository.existsById(Long.parseLong(id,10));
    }

    private boolean checkLessorInDB(String id){
        return Long.parseLong(id,10) == 1L;
    }
}
