package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class PasswordsNotEqualException extends BaseException {

    public PasswordsNotEqualException() {
        super(
                "As palavras-chaves não coincidem",
                HttpStatus.BAD_REQUEST);
    }
}
