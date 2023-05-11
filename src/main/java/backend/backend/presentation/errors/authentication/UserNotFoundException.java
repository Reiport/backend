package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(
                "Não foi encontrada uma conta com este endereço de e-mail. Por favor, tente novamente.",
                HttpStatus.BAD_REQUEST);
    }
}
