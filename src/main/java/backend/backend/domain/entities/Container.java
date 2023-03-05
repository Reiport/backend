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
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "container")
@Data
@Entity
public class Container {

    @Id
    @Column(nullable = false, updatable = false, length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Boolean isInUse;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model", nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", nullable = false)
    private Type type;

    @OneToMany(mappedBy = "containerLicense")
    private Set<Request> containerLicenseRequests;

    @OneToMany(mappedBy = "containerLicenseSecond")
    private Set<Request> containerLicenseSecondRequests;

}
