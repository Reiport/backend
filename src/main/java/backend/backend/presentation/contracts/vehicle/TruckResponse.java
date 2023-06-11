package backend.backend.presentation.contracts.vehicle;

import java.math.BigDecimal;

import backend.backend.domain.entities.Fuel;
import backend.backend.presentation.contracts.info.ModelResponse;

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

    public TruckResponse() {
    }

    public TruckResponse(String license, Integer power, Integer displacement, BigDecimal tank, String color,
            BigDecimal maxSupportedWeight, Boolean isInUse, Fuel fuel, ModelResponse model) {
        this.license = license;
        this.power = power;
        this.displacement = displacement;
        this.tank = tank;
        this.color = color;
        this.maxSupportedWeight = maxSupportedWeight;
        this.isInUse = isInUse;
        this.fuel = fuel;
        this.model = model;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }

    public BigDecimal getTank() {
        return tank;
    }

    public void setTank(BigDecimal tank) {
        this.tank = tank;
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

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public ModelResponse getModel() {
        return model;
    }

    public void setModel(ModelResponse model) {
        this.model = model;
    }

}
