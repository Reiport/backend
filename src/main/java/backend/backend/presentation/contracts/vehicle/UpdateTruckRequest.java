package backend.backend.presentation.contracts.vehicle;

import java.math.BigDecimal;

public class UpdateTruckRequest {

    private String license;
    private String power;
    private String displacement;
    private double tank;
    private String color;
    private BigDecimal maxSupportedWeight;

    public UpdateTruckRequest() {
    }

    public UpdateTruckRequest(String license, String power, String displacement, double tank, String color,
            BigDecimal maxSupportedWeight) {
        this.license = license;
        this.power = power;
        this.displacement = displacement;
        this.tank = tank;
        this.color = color;
        this.maxSupportedWeight = maxSupportedWeight;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public double getTank() {
        return tank;
    }

    public void setTank(double tank) {
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

}
