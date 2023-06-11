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

@Entity
@Table(name = "requestinfo")
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

    public RequestInfo() {
    }

    public RequestInfo(int id, int state, String companyName, Boolean truckAvailability, Boolean containerAvailability,
            BigDecimal cargoWeight, LocalDate deadline, BigDecimal deliveryPrice, int portDest, String streetDest,
            String postalCodeDest, String localityDest, String countryDest, int portOri, String streetOri,
            String postalCodeOri, String localityOri, String countryOri, String containerLicense,
            String containerLicenseSecond, String vehicleLicense, Guest client, Invoice invoice, LocalDate createdAt) {
        this.id = id;
        this.state = state;
        this.companyName = companyName;
        this.truckAvailability = truckAvailability;
        this.containerAvailability = containerAvailability;
        this.cargoWeight = cargoWeight;
        this.deadline = deadline;
        this.deliveryPrice = deliveryPrice;
        this.portDest = portDest;
        this.streetDest = streetDest;
        this.postalCodeDest = postalCodeDest;
        this.localityDest = localityDest;
        this.countryDest = countryDest;
        this.portOri = portOri;
        this.streetOri = streetOri;
        this.postalCodeOri = postalCodeOri;
        this.localityOri = localityOri;
        this.countryOri = countryOri;
        this.containerLicense = containerLicense;
        this.containerLicenseSecond = containerLicenseSecond;
        this.vehicleLicense = vehicleLicense;
        this.client = client;
        this.invoice = invoice;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getPortDest() {
        return portDest;
    }

    public void setPortDest(int portDest) {
        this.portDest = portDest;
    }

    public String getStreetDest() {
        return streetDest;
    }

    public void setStreetDest(String streetDest) {
        this.streetDest = streetDest;
    }

    public String getPostalCodeDest() {
        return postalCodeDest;
    }

    public void setPostalCodeDest(String postalCodeDest) {
        this.postalCodeDest = postalCodeDest;
    }

    public String getLocalityDest() {
        return localityDest;
    }

    public void setLocalityDest(String localityDest) {
        this.localityDest = localityDest;
    }

    public String getCountryDest() {
        return countryDest;
    }

    public void setCountryDest(String countryDest) {
        this.countryDest = countryDest;
    }

    public int getPortOri() {
        return portOri;
    }

    public void setPortOri(int portOri) {
        this.portOri = portOri;
    }

    public String getStreetOri() {
        return streetOri;
    }

    public void setStreetOri(String streetOri) {
        this.streetOri = streetOri;
    }

    public String getPostalCodeOri() {
        return postalCodeOri;
    }

    public void setPostalCodeOri(String postalCodeOri) {
        this.postalCodeOri = postalCodeOri;
    }

    public String getLocalityOri() {
        return localityOri;
    }

    public void setLocalityOri(String localityOri) {
        this.localityOri = localityOri;
    }

    public String getCountryOri() {
        return countryOri;
    }

    public void setCountryOri(String countryOri) {
        this.countryOri = countryOri;
    }

    public String getContainerLicense() {
        return containerLicense;
    }

    public void setContainerLicense(String containerLicense) {
        this.containerLicense = containerLicense;
    }

    public String getContainerLicenseSecond() {
        return containerLicenseSecond;
    }

    public void setContainerLicenseSecond(String containerLicenseSecond) {
        this.containerLicenseSecond = containerLicenseSecond;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public Guest getClient() {
        return client;
    }

    public void setClient(Guest client) {
        this.client = client;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
