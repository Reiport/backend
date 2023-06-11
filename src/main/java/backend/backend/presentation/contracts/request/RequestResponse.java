package backend.backend.presentation.contracts.request;

import java.math.BigDecimal;

import backend.backend.presentation.contracts.invoice.InvoiceResponse;
import backend.backend.presentation.contracts.worker.WorkerResponse;

public class RequestResponse {

    private int id;
    private int state;
    private String companyName;
    private Boolean truckAvailability;
    private Boolean containerAvailability;
    private BigDecimal cargoWeight;
    private String deadline;
    private BigDecimal deliveryPrice;
    private Integer portDest;
    private String streetDest;
    private String postalCodeDest;
    private String localityDest;
    private String countryDest;
    private Integer portOri;
    private String streetOri;
    private String postalCodeOri;
    private String localityOri;
    private String countryOri;
    private String containerLicense;
    private String containerLicenseSecond;
    private String license;
    private WorkerResponse client;
    private InvoiceResponse invoice;
    private String createdAt;

    public RequestResponse() {
    }

    public RequestResponse(int id, int state, String companyName, Boolean truckAvailability,
            Boolean containerAvailability, BigDecimal cargoWeight, String deadline, BigDecimal deliveryPrice,
            Integer portDest, String streetDest, String postalCodeDest, String localityDest, String countryDest,
            Integer portOri, String streetOri, String postalCodeOri, String localityOri, String countryOri,
            String containerLicense, String containerLicenseSecond, String license, WorkerResponse client,
            InvoiceResponse invoice, String createdAt) {
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
        this.license = license;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public WorkerResponse getClient() {
        return client;
    }

    public void setClient(WorkerResponse client) {
        this.client = client;
    }

    public InvoiceResponse getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceResponse invoice) {
        this.invoice = invoice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
