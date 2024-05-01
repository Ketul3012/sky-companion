/**
 * Utility class to have some important methods regarding JWT generation and validation.
 * This class provides methods to generate and validate JSON Web Tokens (JWT) for user authentication.
 * It uses a secret key to sign the tokens and provides functionality to generate a token from user details
 * and validate a token to retrieve the user ID.
 * <p>
 * This file is important to the project because it enables secure authentication and authorization
 * by generating and validating JWT tokens. JWTs are widely used in modern web applications as a means
 * of securely transmitting information between parties. By using this utility class, the project can
 * ensure that only valid and authenticated users can access protected resources.
 */

package com.sky.companion.security.jwt;


import com.sky.companion.authentication.common.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * Utility class to have some important methods regarding JWT generation and validation
 */
@Component
public class JWTUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Key hmacKey;

    /**
     * method to generate {@link Key} using secret key
     */
    @PostConstruct
    private void buildHMACKey() {
        hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secretKey),
                SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * method to generate JWT token from {@link UserEntity} details
     *
     * @param userEntity
     * @return
     */
    public String generateJWTToken(UserEntity userEntity) {
        return Jwts.builder().setId(userEntity.getId().toString()).setSubject(userEntity.getFirstName()).signWith(hmacKey).compact();
    }


    /**
     * method to validate jwt token and return user id if token is valid, in case of jwt token parsing exception it will return null,
     * so calling function must understand null as a invalid token.
     *
     * @param jwtToken
     * @return
     */
    public Integer validateJWTTokenAndGetUserId(String jwtToken) {
        try {
            jwtToken = jwtToken.replace(JWTConstants.PREFIX, "");
            return Integer.parseInt(Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(jwtToken).getBody().getId());
        } catch (Exception e) {
            return null;
        }
    }
}
