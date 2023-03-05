package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ForgotPasswordRequest {

    @Email
    private String email;

}
