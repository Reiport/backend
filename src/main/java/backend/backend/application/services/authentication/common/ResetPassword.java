package backend.backend.application.services.authentication.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResetPassword {

    private String password;
    private String confirmPassword;
    private String token;

}
