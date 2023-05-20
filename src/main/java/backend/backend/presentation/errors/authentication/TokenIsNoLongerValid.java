package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class TokenIsNoLongerValid extends BaseException {

    public TokenIsNoLongerValid() {
        super(
                "Por favor, tente autenticar-se novamente mais tarde.",
                HttpStatus.BAD_REQUEST);
    }

}
