package com.auth.demo.utils;


import com.auth.demo.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String SECRET = "my_secret";
    private static final long EXPIRATION = 3600L; // ms

    public static String createToken(User user) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("password", user.getPassword())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static boolean verifyToken(String token) {
        try{
            final String jwtToken = token.substring(7);
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
