package backend.backend.presentation.contracts.container;

import java.math.BigDecimal;

import backend.backend.domain.entities.Type;

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

    public ContainerRequest() {
    }

    public ContainerRequest(String brand, String model, String license, BigDecimal width, BigDecimal length,
            BigDecimal depth, String color, BigDecimal maxSupportedWeight, Type type) {
        this.brand = brand;
        this.model = model;
        this.license = license;
        this.width = width;
        this.length = length;
        this.depth = depth;
        this.color = color;
        this.maxSupportedWeight = maxSupportedWeight;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
