package com.seclass.sepcamp.components;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {
    @Value("${jwt.expire-date}")
    private long expire_date;
    @Value("${jwt.token-secret}")
    private String token_secrete;

    public String getUsernameFromToken(String token) {
        DecodedJWT res = decode(token);
        if(res == null) return null;
        return res.getClaim("username").asString();
    }

    public String generate(String username, String password) {
        try {
            Date expire_date = new Date(System.currentTimeMillis() + this.expire_date);
            Algorithm algorithm = Algorithm.HMAC256(this.token_secrete);
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .withExpiresAt(expire_date)
                    .sign(algorithm);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private DecodedJWT decode(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.token_secrete);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verify(String token, UserDetails userDetails) {
        DecodedJWT result = decode(token);
        if(result == null) return false;
        return result.getClaim("username").asString().equals(userDetails.getUsername())
                && result.getClaim("password").asString().equals(userDetails.getPassword())
                && result.getExpiresAt().before(new Date());
    }
}
