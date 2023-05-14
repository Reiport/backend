package backend.backend.presentation.contracts.worker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DriverResponse {
    private Boolean hasAdr;
    private Boolean hasCam;
    private String cc;
    private Boolean isWorking;
    private WorkerResponse guest;
}
