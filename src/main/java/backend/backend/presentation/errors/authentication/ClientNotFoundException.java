package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class ClientNotFoundException extends BaseException {

    public ClientNotFoundException() {
        super(
                "No client was found!",
                HttpStatus.BAD_REQUEST);
    }

}
