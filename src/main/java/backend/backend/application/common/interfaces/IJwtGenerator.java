package backend.backend.application.common.interfaces;

import java.util.Collection;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface IJwtGenerator {

    String generateSimpleToken();

    String generateToken(String id, String email, Collection<String> role);

    DecodedJWT decodeToken(String token);

    boolean validateToken(String token);

}
