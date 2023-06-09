package backend.backend.infrastructure.providers.authentication;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.security.access.AccessDeniedException;

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

    // TODO: Fix the role acess
    @Override
    public String generateToken(String uuid, String email, Collection<String> role, long duration) {

        String token = "";

        try {

            token = JWT.create()
                    .withIssuer("Reiport")
                    .withClaim("email", email)
                    .withClaim("role", (String) role.toArray()[0])
                    .withClaim("token_id", uuid)
                    .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                    .sign(algorithm);

        } catch (Exception e) {
            throw new RuntimeException("Error creating a new jwt token!");
        }

        return token;

    }

    // TODO: Authentication Error here
    @Override
    public DecodedJWT decodeToken(String token) {

        DecodedJWT decodedJWT;

        try {

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Reiport")
                    .build();

            decodedJWT = verifier.verify(token);

        } catch (Exception e) {
            throw new AccessDeniedException("Error Decoding the token!");
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
