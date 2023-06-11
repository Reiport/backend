package backend.backend.presentation.contracts.vehicle;

import java.math.BigDecimal;

import backend.backend.domain.entities.Fuel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TruckRequest {

    @NotBlank(message = "Preencher o campo: marca")
    private String brand;

    @NotBlank(message = "Preencher o campo: modelo")
    private String model;

    @NotBlank(message = "Preencher o campo: licensa")
    private String license;

    @NotNull(message = "Preencher o campo: potência")
    @Min(0)
    private int power;

    @NotNull(message = "Preencher o campo: cilindrada")
    @Min(0)
    private int displacement;

    @NotNull(message = "Preencher o campo: tanque")
    @Min(0)
    private BigDecimal tank;

    @NotEmpty(message = "Preencher o campo: cor")
    @Size(min = 3, message = "O campo cor deve pelo menos de 4 a 16 caracteres")
    private String color;

    @NotNull(message = "Preencher o campo: maximo peso suportado")
    @Min(0)
    private BigDecimal maxSupportedWeight;

    @NotNull(message = "Preencher o campo: combústivel")
    private Fuel fuel;

    public TruckRequest() {
    }

    public TruckRequest(String brand, String model, String license, int power, int displacement, BigDecimal tank,
            String color, BigDecimal maxSupportedWeight, Fuel fuel) {
        this.brand = brand;
        this.model = model;
        this.license = license;
        this.power = power;
        this.displacement = displacement;
        this.tank = tank;
        this.color = color;
        this.maxSupportedWeight = maxSupportedWeight;
        this.fuel = fuel;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
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

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

}
