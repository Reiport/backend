package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "vehicle")
@Data
@Entity
@NoArgsConstructor
public class Vehicle {

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
    private LocalDate createdAt = LocalDate.now();

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model", nullable = false)
    private Model model;

    @OneToMany(mappedBy = "license")
    private Set<Request> licenseRequests;

}
