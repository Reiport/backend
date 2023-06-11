package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "model")
@Entity
public class Model {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDate launchDate;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private Set<Container> modelContainers;

    @OneToMany(mappedBy = "model")
    private Set<Vehicle> modelVehicles;

    public Model() {
    }

    public Model(Integer id, String name, LocalDate launchDate, LocalDate createdAt, LocalDate updatedAt,
            LocalDate deletedAt, Brand brand, Set<Container> modelContainers, Set<Vehicle> modelVehicles) {
        this.id = id;
        this.name = name;
        this.launchDate = launchDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.brand = brand;
        this.modelContainers = modelContainers;
        this.modelVehicles = modelVehicles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Container> getModelContainers() {
        return modelContainers;
    }

    public void setModelContainers(Set<Container> modelContainers) {
        this.modelContainers = modelContainers;
    }

    public Set<Vehicle> getModelVehicles() {
        return modelVehicles;
    }

    public void setModelVehicles(Set<Vehicle> modelVehicles) {
        this.modelVehicles = modelVehicles;
    }

}
