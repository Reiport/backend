package backend.backend.infrastructure.providers.authentication;

import java.util.Date;
import java.util.Random;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.config.settings.JwtConfiguration;

public class JwtGenerator implements IJwtGenerator {

    private final Algorithm algorithm;

    public JwtGenerator(JwtConfiguration jwtConfiguration) {
        algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());
    }

    @Override
    public String generateToken(String uuid, String email) {

        String token = "";

        try {

            token = JWT.create()
                    .withIssuer("Reiport")
                    .withClaim("email", email)
                    .withClaim("token_id", uuid)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                    .sign(algorithm);

        } catch (Exception e) {
            throw new RuntimeException("Error creating a new jwt token!");
        }

        return token;

    }

    @Override
    public DecodedJWT decodeToken(String token) {

        DecodedJWT decodedJWT;

        try {

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Reiport")
                    .build();

            decodedJWT = verifier.verify(token);

        } catch (Exception e) {
            throw new RuntimeException("Error Decoding the token!");
        }

        return decodedJWT;

    }

    @Override
    public boolean validateToken(String token) {
        return decodeToken(token) != null;
    }

    @Override
    public String generateSimpleToken() {

        Random random = new Random();
        StringBuffer buffer = new StringBuffer();

        while (buffer.length() < 16) {
            buffer.append(Integer.toHexString(random.nextInt()));
        }

        return buffer.toString().substring(0, 16);

    }

}