package backend.backend.presentation.contracts.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentCompleteRequest {

    private int requestId;
    private boolean hasClientTruck;
    private boolean hasClientContainer;

}
