package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ForgotPasswordRequest {

    @Email(message = "Por favor introduza um email válido: user@reiport.trl")
    @NotEmpty(message = "Por favor preencha o campo: email")
    private String email;

    public ForgotPasswordRequest() {
    }

    public ForgotPasswordRequest(
            @Email(message = "Por favor introduza um email válido: user@reiport.trl") @NotEmpty(message = "Por favor preencha o campo: email") String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
