package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequest {

    @Email()
    @NotBlank()
    private String email;

    @Size(min = 5, max = 10)
    @NotBlank()
    private String password;

}
