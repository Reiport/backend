package backend.backend.presentation.contracts.container;

import java.math.BigDecimal;

import backend.backend.domain.entities.Type;
import backend.backend.presentation.contracts.model.ModelResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerResponse {

    private String license;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal depth;
    private String color;
    private BigDecimal maxSupportedWeight;
    private Boolean isInUse;
    private Type type;
    private ModelResponse model;

}
