package backend.backend.presentation.contracts.manager;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentCompleteRequest {

    private int requestId;
    private boolean hasClientTruck;
    private boolean hasClientContainer;
    private String vehicleLicense;
    private String containerLicense;
    private int driverId;
    private boolean OptionalDriver = false;
    private int optionalDriverId;
    private BigDecimal kilometers;
    private BigDecimal deliveryPrice;

}
