package backend.backend.presentation.errors.request;

import org.springframework.http.HttpStatus;

import backend.backend.presentation.errors.authentication.BaseException;

public class WasNotFoundAnyGroupRequest extends BaseException {

    public WasNotFoundAnyGroupRequest() {
        super(
                "Não foi encontrado ningêm pertencente a este pedido", HttpStatus.BAD_GATEWAY);
    }

}
