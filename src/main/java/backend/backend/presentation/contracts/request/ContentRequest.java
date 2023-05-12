package backend.backend.presentation.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentRequest {

    private String vehicleLicense;
    private String containerLicense;
    private boolean hasVehicleClient;
    private boolean hasContainerClient;
    private Double cargoWeight;
    private String deadline;
    private Integer portDest;
    private String postalCodeDest;
    private String streetDest;
    private String streetOri;
    private String postalCodeOri;
    private Integer portOri;
    private Integer clientId;

}
