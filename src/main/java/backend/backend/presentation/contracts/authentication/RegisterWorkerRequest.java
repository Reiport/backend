package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterWorkerRequest {

    @NotEmpty(message = "Porfavor preencha o campo: nome própio")
    private String firstName;

    @NotEmpty(message = "Porfavor preencha o campo: último nome")
    private String lastName;

    @NotEmpty(message = "Porfavor preencha o campo: data de nascimento")
    private String birthDate;

    @Size(min = 9, max = 9, message = "Introduza o nif válido com 9 caracteres")
    @NotEmpty(message = "Porfavor preencha o campo: nif")
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

    @NotEmpty(message = "Porfavor preencha o campo: tipo de funcionario")
    private String guestType;

}
