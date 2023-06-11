package backend.backend.presentation.contracts.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ContentRequest {

    @NotEmpty(message = "Preencher campo: nome da empresa")
    private String companyName;

    // @NotEmpty(message = "Preencher campo: licença do veiculo")
    private String vehicleLicense;

    // @NotEmpty(message = "Preencher campo: licença do contentor")
    private String containerLicense;

    private String containerLicenseSecond;

    @NotNull(message = "Preencher campo: veiculo é do cliente")
    private boolean hasVehicleClient;

    @NotNull(message = "Preencher campo: contentor é do cliente")
    private boolean hasContainerClient;

    @NotNull(message = "Preencher campo: peso da carga")
    private Double cargoWeight;

    @NotNull(message = "Preencher campo: deadline")
    private String deadline;

    @NotNull(message = "Preencher campo: porta destino")
    private Integer portDest;

    @NotEmpty(message = "Preencher campo: código postal destino")
    private String postalCodeDest;

    @NotEmpty(message = "Preencher campo: rua destino")
    private String streetDest;

    @NotEmpty(message = "Preencher campo: rua origem")
    private String streetOri;

    @NotEmpty(message = "Preencher campo: código postal origem")
    private String postalCodeOri;

    @NotNull(message = "Preencher campo: porta origem")
    private Integer portOri;

    @NotNull(message = "Preencher campo: id do cliente")
    private Integer clientId;

    public ContentRequest() {
    }

    public ContentRequest(String companyName, String vehicleLicense, String containerLicense,
            String containerLicenseSecond, boolean hasVehicleClient, boolean hasContainerClient, Double cargoWeight,
            String deadline, Integer portDest, String postalCodeDest, String streetDest, String streetOri,
            String postalCodeOri, Integer portOri, Integer clientId) {
        this.companyName = companyName;
        this.vehicleLicense = vehicleLicense;
        this.containerLicense = containerLicense;
        this.containerLicenseSecond = containerLicenseSecond;
        this.hasVehicleClient = hasVehicleClient;
        this.hasContainerClient = hasContainerClient;
        this.cargoWeight = cargoWeight;
        this.deadline = deadline;
        this.portDest = portDest;
        this.postalCodeDest = postalCodeDest;
        this.streetDest = streetDest;
        this.streetOri = streetOri;
        this.postalCodeOri = postalCodeOri;
        this.portOri = portOri;
        this.clientId = clientId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
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

    public boolean isHasVehicleClient() {
        return hasVehicleClient;
    }

    public void setHasVehicleClient(boolean hasVehicleClient) {
        this.hasVehicleClient = hasVehicleClient;
    }

    public boolean isHasContainerClient() {
        return hasContainerClient;
    }

    public void setHasContainerClient(boolean hasContainerClient) {
        this.hasContainerClient = hasContainerClient;
    }

    public Double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getPortDest() {
        return portDest;
    }

    public void setPortDest(Integer portDest) {
        this.portDest = portDest;
    }

    public String getPostalCodeDest() {
        return postalCodeDest;
    }

    public void setPostalCodeDest(String postalCodeDest) {
        this.postalCodeDest = postalCodeDest;
    }

    public String getStreetDest() {
        return streetDest;
    }

    public void setStreetDest(String streetDest) {
        this.streetDest = streetDest;
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

    public Integer getPortOri() {
        return portOri;
    }

    public void setPortOri(Integer portOri) {
        this.portOri = portOri;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

}
