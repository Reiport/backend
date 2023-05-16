package backend.backend.presentation.contracts.vehicle;

import java.math.BigDecimal;

import backend.backend.domain.entities.Fuel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckRequest {

    private String brand;
    private String model;
    private String license;
    private int power;
    private int displacement;
    private BigDecimal tank;
    private String color;
    private BigDecimal maxSupportedWeight;
    private Fuel fuel;

}
