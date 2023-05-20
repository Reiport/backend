package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class PasswordDontMatchException extends BaseException {

    public PasswordDontMatchException() {
        super(
                "O email ou a palavra-chave está incorreto!",
                HttpStatus.BAD_REQUEST);
    }

}
