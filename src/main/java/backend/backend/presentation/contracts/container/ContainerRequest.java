package backend.backend.presentation.contracts.container;

import java.math.BigDecimal;

import backend.backend.domain.entities.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerRequest {

    private String brand;
    private String model;
    private String license;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal depth;
    private String color;
    private BigDecimal maxSupportedWeight;
    private Type type;

}
