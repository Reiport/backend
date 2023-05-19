package backend.backend.presentation.errors.worker;

import org.springframework.http.HttpStatus;

import backend.backend.presentation.errors.authentication.BaseException;

public class WorkerNotFoundException extends BaseException {

    public WorkerNotFoundException() {
        super(
                "Funcionário não encontrado!",
                HttpStatus.BAD_REQUEST);
    }

}
