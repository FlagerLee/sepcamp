package com.seclass.sepcamp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTokenUtil {
    @Value("${jwt.expire-date}")
    private long expire_date;
    @Value("${jwt.token-secrete}")
    private String token_secrete;

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

    public boolean verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.token_secrete);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT res = verifier.verify(token);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
