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

@Table(name = "container")
@Entity
public class Container {

    public Container() {
    }

    @Id
    @Column(nullable = false, updatable = false, length = 11)
    private String license;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal width;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal length;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal depth;

    @Column(nullable = false, length = 50)
    private String color;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal maxSupportedWeight;

    @Column(nullable = false)
    private Boolean isInUse = false;

    @Column(nullable = false)
    private Type type;

    @Column
    private LocalDate createdAt = LocalDate.now();

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model", nullable = false)
    private Model model;

    @OneToMany(mappedBy = "containerLicense")
    private Set<Request> containerLicenseRequests;

    @OneToMany(mappedBy = "containerLicenseSecond")
    private Set<Request> containerLicenseSecondRequests;

    public Container(String license, BigDecimal width, BigDecimal length, BigDecimal depth, String color,
            BigDecimal maxSupportedWeight, Boolean isInUse, Type type, LocalDate createdAt, LocalDate updatedAt,
            LocalDate deletedAt, Model model, Set<Request> containerLicenseRequests,
            Set<Request> containerLicenseSecondRequests) {
        this.license = license;
        this.width = width;
        this.length = length;
        this.depth = depth;
        this.color = color;
        this.maxSupportedWeight = maxSupportedWeight;
        this.isInUse = isInUse;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.model = model;
        this.containerLicenseRequests = containerLicenseRequests;
        this.containerLicenseSecondRequests = containerLicenseSecondRequests;
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

    public Set<Request> getContainerLicenseRequests() {
        return containerLicenseRequests;
    }

    public void setContainerLicenseRequests(Set<Request> containerLicenseRequests) {
        this.containerLicenseRequests = containerLicenseRequests;
    }

    public Set<Request> getContainerLicenseSecondRequests() {
        return containerLicenseSecondRequests;
    }

    public void setContainerLicenseSecondRequests(Set<Request> containerLicenseSecondRequests) {
        this.containerLicenseSecondRequests = containerLicenseSecondRequests;
    }

}
