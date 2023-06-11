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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "request")
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

    public Request(
            String companyName,
            Vehicle truck, Container container,
            BigDecimal cargoWeight, LocalDate deadline, Integer portDest, String streetDest, Integer portOri,
            String streetOri, PostalCode postalCodeDest, PostalCode postalCodeOri,
            Guest client) {
        this.companyName = companyName;
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
        this.deliveryPrice = BigDecimal.valueOf(1);
    }

    public Request(Vehicle truck, Container container, Container secContainer,
            BigDecimal cargoWeight, LocalDate deadline, Integer portDest, String streetDest, Integer portOri,
            String streetOri, PostalCode postalCodeDest, PostalCode postalCodeOri,
            Guest client) {
        this.license = truck;
        this.containerLicense = container;
        this.containerLicenseSecond = secContainer;
        this.cargoWeight = cargoWeight;
        this.deadline = deadline;
        this.portDest = portDest;
        this.streetDest = streetDest;
        this.portOri = portOri;
        this.streetOri = streetOri;
        this.postalCodeDest = postalCodeDest;
        this.postalCodeOri = postalCodeOri;
        this.client = client;
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

    @OneToMany(mappedBy = "request")
    private Set<Invoice> requestInvoices;

    @OneToMany(mappedBy = "request")
    private Set<DriverGroup> requestDriverGroups;

    @OneToMany(mappedBy = "request")
    private Set<HistoricStates> requestHistoricStatess;

    public Request(Integer id, String companyName, Boolean truckAvailability, Boolean containerAvailability,
            BigDecimal cargoWeight, LocalDate deadline, Integer portDest, String streetDest, Integer portOri,
            String streetOri, BigDecimal deliveryPrice, LocalDate createdAt, LocalDate updatedAt, LocalDate deletedAt,
            PostalCode postalCodeDest, PostalCode postalCodeOri, Container containerLicense,
            Container containerLicenseSecond, Vehicle license, Guest client, Set<Invoice> requestInvoices,
            Set<DriverGroup> requestDriverGroups, Set<HistoricStates> requestHistoricStatess) {
        this.id = id;
        this.companyName = companyName;
        this.truckAvailability = truckAvailability;
        this.containerAvailability = containerAvailability;
        this.cargoWeight = cargoWeight;
        this.deadline = deadline;
        this.portDest = portDest;
        this.streetDest = streetDest;
        this.portOri = portOri;
        this.streetOri = streetOri;
        this.deliveryPrice = deliveryPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.postalCodeDest = postalCodeDest;
        this.postalCodeOri = postalCodeOri;
        this.containerLicense = containerLicense;
        this.containerLicenseSecond = containerLicenseSecond;
        this.license = license;
        this.client = client;
        this.requestInvoices = requestInvoices;
        this.requestDriverGroups = requestDriverGroups;
        this.requestHistoricStatess = requestHistoricStatess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getTruckAvailability() {
        return truckAvailability;
    }

    public void setTruckAvailability(Boolean truckAvailability) {
        this.truckAvailability = truckAvailability;
    }

    public Boolean getContainerAvailability() {
        return containerAvailability;
    }

    public void setContainerAvailability(Boolean containerAvailability) {
        this.containerAvailability = containerAvailability;
    }

    public BigDecimal getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(BigDecimal cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Integer getPortDest() {
        return portDest;
    }

    public void setPortDest(Integer portDest) {
        this.portDest = portDest;
    }

    public String getStreetDest() {
        return streetDest;
    }

    public void setStreetDest(String streetDest) {
        this.streetDest = streetDest;
    }

    public Integer getPortOri() {
        return portOri;
    }

    public void setPortOri(Integer portOri) {
        this.portOri = portOri;
    }

    public String getStreetOri() {
        return streetOri;
    }

    public void setStreetOri(String streetOri) {
        this.streetOri = streetOri;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
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

    public PostalCode getPostalCodeDest() {
        return postalCodeDest;
    }

    public void setPostalCodeDest(PostalCode postalCodeDest) {
        this.postalCodeDest = postalCodeDest;
    }

    public PostalCode getPostalCodeOri() {
        return postalCodeOri;
    }

    public void setPostalCodeOri(PostalCode postalCodeOri) {
        this.postalCodeOri = postalCodeOri;
    }

    public Container getContainerLicense() {
        return containerLicense;
    }

    public void setContainerLicense(Container containerLicense) {
        this.containerLicense = containerLicense;
    }

    public Container getContainerLicenseSecond() {
        return containerLicenseSecond;
    }

    public void setContainerLicenseSecond(Container containerLicenseSecond) {
        this.containerLicenseSecond = containerLicenseSecond;
    }

    public Vehicle getLicense() {
        return license;
    }

    public void setLicense(Vehicle license) {
        this.license = license;
    }

    public Guest getClient() {
        return client;
    }

    public void setClient(Guest client) {
        this.client = client;
    }

    public Set<Invoice> getRequestInvoices() {
        return requestInvoices;
    }

    public void setRequestInvoices(Set<Invoice> requestInvoices) {
        this.requestInvoices = requestInvoices;
    }

    public Set<DriverGroup> getRequestDriverGroups() {
        return requestDriverGroups;
    }

    public void setRequestDriverGroups(Set<DriverGroup> requestDriverGroups) {
        this.requestDriverGroups = requestDriverGroups;
    }

    public Set<HistoricStates> getRequestHistoricStatess() {
        return requestHistoricStatess;
    }

    public void setRequestHistoricStatess(Set<HistoricStates> requestHistoricStatess) {
        this.requestHistoricStatess = requestHistoricStatess;
    }

}
