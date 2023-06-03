package backend.backend.presentation.contracts.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentRequest {

    @NotEmpty(message = "Preencher campo: nome da empresa")
    private String companyName;

    // @NotEmpty(message = "Preencher campo: licença do veiculo")
    private String vehicleLicense;

    // @NotEmpty(message = "Preencher campo: licença do contentor")
    private String containerLicense;

    private String containerLicenseSecond;

    @NotNull(message = "Preencher campo: veiculo é do cliente")
    private boolean hasVehicleClient;

    @NotNull(message = "Preencher campo: contentor é do cliente")
    private boolean hasContainerClient;

    @NotNull(message = "Preencher campo: peso da carga")
    private Double cargoWeight;

    @NotNull(message = "Preencher campo: deadline")
    private String deadline;

    @NotNull(message = "Preencher campo: porta destino")
    private Integer portDest;

    @NotEmpty(message = "Preencher campo: código postal destino")
    private String postalCodeDest;

    @NotEmpty(message = "Preencher campo: rua destino")
    private String streetDest;

    @NotEmpty(message = "Preencher campo: rua origem")
    private String streetOri;

    @NotEmpty(message = "Preencher campo: código postal origem")
    private String postalCodeOri;

    @NotNull(message = "Preencher campo: porta origem")
    private Integer portOri;

    @NotNull(message = "Preencher campo: id do cliente")
    private Integer clientId;

}
