package backend.backend.presentation.contracts.request;

import java.math.BigDecimal;

import backend.backend.domain.entities.Invoice;
import backend.backend.presentation.contracts.worker.WorkerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
    private Invoice invoice;
    private String createdAt;

}
