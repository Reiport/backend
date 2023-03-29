package backend.backend.presentation.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentRequest {

    private Double cargoWeight;
    private String deadline;
    private Integer portDest;
    private String postalCodeDest;
    private String streetDest;
    private String streetOri;
    private String postalCodeOri;
    private Integer portOri;
    private Double deliveryPrice;
    private Integer clientId;

}
