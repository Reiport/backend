package backend.backend.presentation.contracts.manager;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class ContentCompleteRequest {

    @NotNull(message = "Preencher campo: id do pedido")
    private int requestId;

    @NotNull(message = "Preencher campo: cliente tem veiculo")
    private boolean hasClientTruck;

    @NotNull(message = "Preencher campo: cliente tem contentor")
    private boolean hasClientContainer;

    private String vehicleLicense;

    private String containerLicense;

    @NotNull(message = "Preencher campo: id do motorista")
    private int driverId;

    private boolean OptionalDriver = false;

    private int optionalDriverId;

    @NotNull(message = "Preencher campo: kilometros")
    private BigDecimal kilometers;

    @NotNull(message = "Preencher campo: preço final")
    private BigDecimal deliveryPrice;

    public ContentCompleteRequest() {
    }

    public ContentCompleteRequest(int requestId, boolean hasClientTruck, boolean hasClientContainer,
            String vehicleLicense, String containerLicense, int driverId, boolean optionalDriver, int optionalDriverId,
            BigDecimal kilometers, BigDecimal deliveryPrice) {
        this.requestId = requestId;
        this.hasClientTruck = hasClientTruck;
        this.hasClientContainer = hasClientContainer;
        this.vehicleLicense = vehicleLicense;
        this.containerLicense = containerLicense;
        this.driverId = driverId;
        OptionalDriver = optionalDriver;
        this.optionalDriverId = optionalDriverId;
        this.kilometers = kilometers;
        this.deliveryPrice = deliveryPrice;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public boolean isHasClientTruck() {
        return hasClientTruck;
    }

    public void setHasClientTruck(boolean hasClientTruck) {
        this.hasClientTruck = hasClientTruck;
    }

    public boolean isHasClientContainer() {
        return hasClientContainer;
    }

    public void setHasClientContainer(boolean hasClientContainer) {
        this.hasClientContainer = hasClientContainer;
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

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public boolean isOptionalDriver() {
        return OptionalDriver;
    }

    public void setOptionalDriver(boolean optionalDriver) {
        OptionalDriver = optionalDriver;
    }

    public int getOptionalDriverId() {
        return optionalDriverId;
    }

    public void setOptionalDriverId(int optionalDriverId) {
        this.optionalDriverId = optionalDriverId;
    }

    public BigDecimal getKilometers() {
        return kilometers;
    }

    public void setKilometers(BigDecimal kilometers) {
        this.kilometers = kilometers;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

}
