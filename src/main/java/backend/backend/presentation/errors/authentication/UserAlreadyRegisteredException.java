package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class UserAlreadyRegisteredException extends BaseException {

    public UserAlreadyRegisteredException() {
        super(
                "Este utilizador já se encontra registado!",
                HttpStatus.BAD_REQUEST);
    }
}
