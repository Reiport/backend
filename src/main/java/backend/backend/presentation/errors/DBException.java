package backend.backend.presentation.errors;

import org.springframework.http.HttpStatus;

import backend.backend.presentation.errors.authentication.BaseException;

public class DBException extends BaseException {

    public DBException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
