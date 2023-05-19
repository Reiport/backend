package backend.backend.presentation.contracts.vehicle;

import java.math.BigDecimal;

import backend.backend.domain.entities.Fuel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckRequest {

    @NotBlank(message = "Preencher o campo: marca")
    private String brand;

    @NotBlank(message = "Preencher o campo: modelo")
    private String model;

    @NotBlank(message = "Preencher o campo: licensa")
    private String license;

    @NotNull(message = "Preencher o campo: potência")
    @Min(0)
    private int power;

    @NotNull(message = "Preencher o campo: cilindrada")
    @Min(0)
    private int displacement;

    @NotNull(message = "Preencher o campo: tanque")
    @Min(0)
    private BigDecimal tank;

    @NotEmpty(message = "Preencher o campo: cor")
    @Size(min = 3, message = "O campo cor deve pelo menos de 4 a 16 caracteres")
    private String color;

    @NotNull(message = "Preencher o campo: maximo peso suportado")
    @Min(0)
    private BigDecimal maxSupportedWeight;

    @NotNull(message = "Preencher o campo: combústivel")
    private Fuel fuel;

}
