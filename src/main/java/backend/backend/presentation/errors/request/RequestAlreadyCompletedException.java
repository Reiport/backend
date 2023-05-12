package backend.backend.presentation.errors.request;

import org.springframework.http.HttpStatus;

import backend.backend.presentation.errors.authentication.BaseException;

public class RequestAlreadyCompletedException extends BaseException {

    public RequestAlreadyCompletedException() {
        super(
                "O pedido já foi verificado por um gestor!",
                HttpStatus.BAD_REQUEST);
    }

}
