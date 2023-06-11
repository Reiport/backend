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

@Table(name = "driver")
@Entity
public class Driver {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean hasAdr;

    @Column(nullable = false)
    private Boolean hasCam;

    @Column(nullable = false, precision = 10, scale = 2)
    private String cc;

    @Column(nullable = false)
    private Boolean isWorking;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    private Guest guest_id;

    @OneToMany(mappedBy = "driver")
    private Set<DriverGroup> driverDriverGroups;

    public Driver() {
    }

    public Driver(Long id, Boolean hasAdr, Boolean hasCam, String cc, Boolean isWorking, LocalDate createdAt,
            LocalDate updatedAt, LocalDate deletedAt, Guest guest_id, Set<DriverGroup> driverDriverGroups) {
        this.id = id;
        this.hasAdr = hasAdr;
        this.hasCam = hasCam;
        this.cc = cc;
        this.isWorking = isWorking;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.guest_id = guest_id;
        this.driverDriverGroups = driverDriverGroups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasAdr() {
        return hasAdr;
    }

    public void setHasAdr(Boolean hasAdr) {
        this.hasAdr = hasAdr;
    }

    public Boolean getHasCam() {
        return hasCam;
    }

    public void setHasCam(Boolean hasCam) {
        this.hasCam = hasCam;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Boolean getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(Boolean isWorking) {
        this.isWorking = isWorking;
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

    public Guest getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(Guest guest_id) {
        this.guest_id = guest_id;
    }

    public Set<DriverGroup> getDriverDriverGroups() {
        return driverDriverGroups;
    }

    public void setDriverDriverGroups(Set<DriverGroup> driverDriverGroups) {
        this.driverDriverGroups = driverDriverGroups;
    }

}
