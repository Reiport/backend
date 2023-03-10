package backend.backend.presentation.contracts.authentication;

import java.time.LocalDate;

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

    @NotBlank()
    private String firstName;

    @NotBlank()
    private String lastName;

    @NotBlank()
    private LocalDate birthDate;

    @Size(min = 9, max = 9)
    @NotBlank()
    private String nif;

    @NotBlank()
    private String street;

    @NotBlank()
    private Integer port;

    @Size(min = 9, max = 9)
    @NotBlank()
    private String telephone;

    @Size(min = 8, max = 8)
    @NotBlank()
    private String postalCode;

    @NotBlank()
    private String guestType;

}
