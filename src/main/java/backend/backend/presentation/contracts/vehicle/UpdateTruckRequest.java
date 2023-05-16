package backend.backend.presentation.contracts.vehicle;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTruckRequest {

    private String license;
    private String power;
    private String displacement;
    private double tank;
    private String color;
    private BigDecimal maxSupportedWeight;

}
