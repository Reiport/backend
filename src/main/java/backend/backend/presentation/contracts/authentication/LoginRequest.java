package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequest {

    @Email(message = "Porfavor introduza um email valido: user@reiport.trl")
    @NotEmpty(message = "Porfavor preencha o campo: email")
    private String email;

    @Size(min = 8, max = 16, message = "A password tem de ter pelo menos de 8 a 16 caracteres")
    @NotEmpty(message = "Porfavor preencha o campo: email")
    private String password;

}
