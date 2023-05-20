package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ForgotPasswordRequest {

    @Email(message = "Por favor introduza um email válido: user@reiport.trl")
    @NotEmpty(message = "Por favor preencha o campo: email")
    private String email;

}
