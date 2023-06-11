package backend.backend.presentation.contracts.container;

import java.math.BigDecimal;

import backend.backend.domain.entities.Type;
import backend.backend.presentation.contracts.info.ModelResponse;

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

    public ContainerResponse() {
    }

    public ContainerResponse(String license, BigDecimal width, BigDecimal length, BigDecimal depth, String color,
            BigDecimal maxSupportedWeight, Boolean isInUse, Type type, ModelResponse model) {
        this.license = license;
        this.width = width;
        this.length = length;
        this.depth = depth;
        this.color = color;
        this.maxSupportedWeight = maxSupportedWeight;
        this.isInUse = isInUse;
        this.type = type;
        this.model = model;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getMaxSupportedWeight() {
        return maxSupportedWeight;
    }

    public void setMaxSupportedWeight(BigDecimal maxSupportedWeight) {
        this.maxSupportedWeight = maxSupportedWeight;
    }

    public Boolean getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(Boolean isInUse) {
        this.isInUse = isInUse;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ModelResponse getModel() {
        return model;
    }

    public void setModel(ModelResponse model) {
        this.model = model;
    }

}
