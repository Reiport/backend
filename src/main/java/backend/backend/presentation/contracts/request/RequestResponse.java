package backend.backend.presentation.contracts.request;

import java.math.BigDecimal;

import backend.backend.presentation.contracts.worker.WorkerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestResponse {

    private Boolean truckAvailability;
    private Boolean containerAvailability;
    private BigDecimal cargoWeight;
    private String deadline;
    private Integer portDest;
    private String streetDest;
    private Integer portOri;
    private String streetOri;
    private BigDecimal deliveryPrice;
    private String postalCodeDest;
    private String postalCodeOri;
    private String containerLicense;
    private String containerLicenseSecond;
    private String license;
    private WorkerResponse client;
    private int invoice_id;

}
