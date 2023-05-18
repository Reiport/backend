package backend.backend.presentation.errors;

import org.springframework.http.HttpStatus;

import backend.backend.presentation.errors.authentication.BaseException;

public class CannotAcessRequestException extends BaseException {

    public CannotAcessRequestException() {
        super(
                "Não é possível aceder a este pedido, não tem autorização",
                HttpStatus.FORBIDDEN);
    }

}
