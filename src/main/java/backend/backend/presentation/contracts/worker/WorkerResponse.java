package backend.backend.presentation.contracts.worker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkerResponse {

    private String email;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String nif;

    private String street;

    private Integer port;

    private String telephone;

    private String postalCode;

    private String guestType;

}
