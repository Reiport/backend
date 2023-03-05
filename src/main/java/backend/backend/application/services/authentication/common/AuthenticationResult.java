package backend.backend.application.services.authentication.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResult {

    private String token;
    private String refresh_token;

}
