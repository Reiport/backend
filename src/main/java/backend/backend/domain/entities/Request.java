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

@Table(name = "request")
@Data
@Entity
public class Request {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Boolean truckAvailability = false;

    @Column(nullable = false)
    private Boolean containerAvailability = false;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal cargoWeight;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private Integer portDest;

    @Column(nullable = false, length = 100)
    private String streetDest;

    @Column(nullable = false)
    private Integer portOri;

    @Column(nullable = false, length = 100)
    private String streetOri;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal deliveryPrice;

    @Column
    private LocalDate createdAt = LocalDate.now();

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    public Request() {
    }

    public Request(Vehicle truck, Container container,
            BigDecimal cargoWeight, LocalDate deadline, Integer portDest, String streetDest, Integer portOri,
            String streetOri, PostalCode postalCodeDest, PostalCode postalCodeOri,
            Guest client) {
        this.license = truck;
        this.containerLicense = container;
        this.cargoWeight = cargoWeight;
        this.deadline = deadline;
        this.portDest = portDest;
        this.streetDest = streetDest;
        this.portOri = portOri;
        this.streetOri = streetOri;
        this.postalCodeDest = postalCodeDest;
        this.postalCodeOri = postalCodeOri;
        this.client = client;
        // TODO: valor a pagar é sempre 1
        this.deliveryPrice = BigDecimal.valueOf(1);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code_dest", nullable = false)
    private PostalCode postalCodeDest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code_ori", nullable = false)
    private PostalCode postalCodeOri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_license", nullable = false)
    private Container containerLicense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_license_second")
    private Container containerLicenseSecond;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license", nullable = false)
    private Vehicle license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", nullable = false)
    private Guest client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice", nullable = false)
    private Invoice invoice;

    @OneToMany(mappedBy = "request")
    private Set<DriverGroup> requestDriverGroups;

    @OneToMany(mappedBy = "request")
    private Set<HistoricStates> requestHistoricStatess;

}
