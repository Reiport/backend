package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterWorkerRequest {

    @NotBlank()
    private String firstName;

    @NotBlank()
    private String lastName;

    @NotBlank()
    private String birthDate;

    @Size(min = 9, max = 9)
    @NotBlank()
    private String nif;

    @NotBlank()
    private String street;

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
