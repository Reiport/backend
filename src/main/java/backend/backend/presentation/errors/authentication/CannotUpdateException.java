package backend.backend.presentation.errors.authentication;

import org.springframework.http.HttpStatus;

public class CannotUpdateException extends BaseException {

    public CannotUpdateException() {
        super("Não podes alterar o perfil de outro utilizador", HttpStatus.FORBIDDEN);
    }

}
