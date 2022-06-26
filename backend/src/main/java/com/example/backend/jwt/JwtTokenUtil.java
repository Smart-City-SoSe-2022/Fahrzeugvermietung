package com.example.backend.jwt;

import com.example.backend.repository.EndUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.Serializable;
import java.util.Base64;
import java.util.function.Function;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtTokenUtil implements Serializable {
    private final String secretString = "thisisthesecretkey";
    byte[] secret = secretString.getBytes();
    String chunks[];
    String header;
    String payload;
    String tokenWithoutSignature;
    String signature;
    SignatureAlgorithm sa = HS256;
    private boolean lessor = false;

    SecretKeySpec secretKeySpec = new SecretKeySpec(secret,sa.getJcaName());
    private EndUserRepository endUserRepository;

    public JwtTokenUtil(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    public boolean checkLoggedIn(String token) {
        decode(token);
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa,secretKeySpec);
        if(!validator.isValid(tokenWithoutSignature, signature)){
            return false;
        }
        if(getClaimFromToken(token, Claims::getSubject)!=null){
            checkLessorInDB(getClaimFromToken(token, Claims::getSubject));
            return checkUserInDB(getClaimFromToken(token, Claims::getSubject));
        }else{
            return false;
        }
    }

    public boolean checkUserLessor(){
        return lessor;
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

    private void checkLessorInDB(String id){
        this.lessor = Long.parseLong(id, 10) == 1L;
    }

    private void decode(String token){
        chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        header = new String(decoder.decode(chunks[0]));
        payload = new String(decoder.decode(chunks[1]));

        tokenWithoutSignature = chunks[0] + "." + chunks[1];
        signature = chunks[2];
    }
}
