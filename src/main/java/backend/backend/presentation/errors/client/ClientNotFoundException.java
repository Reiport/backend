package backend.backend.presentation.errors.client;

import org.springframework.http.HttpStatus;

import backend.backend.presentation.errors.authentication.BaseException;

public class ClientNotFoundException extends BaseException {

    public ClientNotFoundException() {
        super(
                "No client was found!",
                HttpStatus.BAD_REQUEST);
    }

}
