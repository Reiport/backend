package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequest {

    @NotBlank(message = "Por favor preencha o campo: password")
    @Size(min = 8, max = 16, message = "A password tem de ter pelo menos de 8 a 16 caracteres")
    private String password;

    @NotBlank(message = "Por favor preencha o campo: password")
    @Size(min = 8, max = 16, message = "A password tem de ter pelo menos de 8 a 16 caracteres")
    private String confirmPassword;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
