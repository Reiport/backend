package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResetPasswordRequest {

    @NotBlank(message = "Porfavor preencha o campo: password")
    @Size(min = 8, max = 16, message = "A password tem de ter pelo menos de 8 a 16 caracteres")
    private String password;

    @NotBlank(message = "Porfavor preencha o campo: password")
    @Size(min = 8, max = 16, message = "A password tem de ter pelo menos de 8 a 16 caracteres")
    private String confirmPassword;

}
