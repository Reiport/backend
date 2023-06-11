package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "vehicle")
@Entity
public class Vehicle {

    public Vehicle(String license, Integer power, Integer displacement, BigDecimal tank, String color,
            BigDecimal maxSupportedWeight, Boolean isInUse, Fuel fuel, Model model) {
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

    @Id
    @Column(nullable = false, updatable = false, length = 8)
    private String license;

    @Column(nullable = false)
    private Integer power;

    @Column(nullable = false)
    private Integer displacement;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal tank;

    @Column(nullable = false, length = 50)
    private String color;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal maxSupportedWeight;

    @Column(nullable = false)
    private Boolean isInUse = false;

    @Column
    private Fuel fuel;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model", nullable = false)
    private Model model;

    @OneToMany(mappedBy = "license")
    private Set<Request> licenseRequests;

    public Vehicle() {
    }

    public Vehicle(String license, Integer power, Integer displacement, BigDecimal tank, String color,
            BigDecimal maxSupportedWeight, Boolean isInUse, Fuel fuel, LocalDate createdAt, LocalDate updatedAt,
            LocalDate deletedAt, Model model, Set<Request> licenseRequests) {
        this.license = license;
        this.power = power;
        this.displacement = displacement;
        this.tank = tank;
        this.color = color;
        this.maxSupportedWeight = maxSupportedWeight;
        this.isInUse = isInUse;
        this.fuel = fuel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.model = model;
        this.licenseRequests = licenseRequests;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Set<Request> getLicenseRequests() {
        return licenseRequests;
    }

    public void setLicenseRequests(Set<Request> licenseRequests) {
        this.licenseRequests = licenseRequests;
    }

}
