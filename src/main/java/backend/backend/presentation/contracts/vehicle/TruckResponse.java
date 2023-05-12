package backend.backend.presentation.contracts.vehicle;

import java.math.BigDecimal;

import backend.backend.domain.entities.Fuel;
import backend.backend.presentation.contracts.model.ModelResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckResponse {

    private String license;
    private Integer power;
    private Integer displacement;
    private BigDecimal tank;
    private String color;
    private BigDecimal maxSupportedWeight;
    private Boolean isInUse;
    private Fuel fuel;
    private ModelResponse model;

}
