package backend.backend.presentation.contracts.manager;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentCompleteRequest {

    @NotNull(message = "Preencher campo: id do pedido")
    private int requestId;

    @NotNull(message = "Preencher campo: cliente tem veiculo")
    private boolean hasClientTruck;

    @NotNull(message = "Preencher campo: cliente tem contentor")
    private boolean hasClientContainer;

    private String vehicleLicense;

    private String containerLicense;

    @NotNull(message = "Preencher campo: id do motorista")
    private int driverId;

    private boolean OptionalDriver = false;

    private int optionalDriverId;

    @NotNull(message = "Preencher campo: kilometros")
    private BigDecimal kilometers;

    @NotNull(message = "Preencher campo: preço final")
    private BigDecimal deliveryPrice;

}
