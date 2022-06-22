package com.example.backend.jwt;

import com.example.backend.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.Serializable;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {
    private final String secretString = "thisisthesecretkey";
    byte[] secret = secretString.getBytes();
    private CustomerRepository customerRepository;

    public JwtTokenUtil(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean checkLoggedIn(String token) {
        return checkUserInDB(getClaimFromToken(token, Claims::getSubject));
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean checkUserInDB(String id){
        return customerRepository.existsById(Long.parseLong(id,10));
    }
}
