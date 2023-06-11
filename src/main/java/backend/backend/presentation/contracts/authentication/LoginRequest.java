package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @Email(message = "Por favor introduza um email valido: user@reiport.trl")
    @NotEmpty(message = "Por favor preencha o campo: email")
    private String email;

    @Size(min = 8, max = 16, message = "A password tem de ter pelo menos de 8 a 16 caracteres")
    @NotEmpty(message = "Por favor preencha o campo: email")
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
