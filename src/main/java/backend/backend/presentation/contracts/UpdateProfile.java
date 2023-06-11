package backend.backend.presentation.contracts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateProfile {

    @Email(message = "Porfavor introduza um email valido: user@reiport.trl")
    @NotEmpty(message = "Porfavor preencha o campo: email")
    private String email;

    @NotBlank(message = "Porfavor preencha o campo: nome própio")
    private String firstName;

    @NotBlank(message = "Porfavor preencha o campo: sobrenome")
    private String lastName;

    @NotBlank(message = "Porfavor preencha o campo: data de nascimento")
    private String birthDate;

    @Size(min = 9, max = 9, message = "Introduza o nif válido com 9 caracteres")
    @NotBlank(message = "Porfavor preencha o campo: nif")
    private String nif;

    @NotEmpty(message = "Porfavor preencha o campo: rua")
    private String street;

    @NotNull(message = "Porfavor preencha o campo: número da porta")
    private Integer port;

    @Size(min = 9, max = 9, message = "Introduza um número de telefone válido com 9 caracteres")
    @NotEmpty(message = "Porfavor preencha o campo: telefone")
    private String telephone;

    @Size(min = 8, max = 8, message = "Introduza um codigo postal válido com 8 caracteres")
    @NotEmpty(message = "Porfavor preencha o campo: codigo postal")
    private String postalCode;

    private String guestType;

}
