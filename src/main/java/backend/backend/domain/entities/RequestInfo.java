package backend.backend.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "requestinfo")
@NoArgsConstructor
public class RequestInfo {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "state")
    private int state;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "truck_availability")
    private Boolean truckAvailability;

    @Column(name = "container_availability")
    private Boolean containerAvailability;

    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "delivery_price")
    private BigDecimal deliveryPrice;

    @Column(name = "port_dest")
    private int portDest;

    @Column(name = "street_dest")
    private String streetDest;

    @Column(name = "postal_code_dest")
    private String postalCodeDest;

    @Column(name = "locality_dest")
    private String localityDest;

    @Column(name = "country_dest")
    private String countryDest;

    @Column(name = "port_ori")
    private int portOri;

    @Column(name = "street_ori")
    private String streetOri;

    @Column(name = "postal_code_ori")
    private String postalCodeOri;

    @Column(name = "locality_ori")
    private String localityOri;

    @Column(name = "country_ori")
    private String countryOri;

    @Column(name = "container_first")
    private String containerLicense;

    @Column(name = "container_second")
    private String containerLicenseSecond;

    @Column(name = "license")
    private String vehicleLicense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", nullable = false)
    private Guest client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice", nullable = false)
    private Invoice invoice;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
