package backend.backend.presentation.errors.request;

import org.springframework.http.HttpStatus;

import backend.backend.presentation.errors.authentication.BaseException;

public class RequestStateViolated extends BaseException {

    public RequestStateViolated() {
        super(
                "Não é possivel realizar esta operação, porque o estado do pedido assim não o permite",
                HttpStatus.BAD_REQUEST);
    }

}
