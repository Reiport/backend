package backend.backend.presentation.contracts.info;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelResponse {

    private String name;
    private LocalDate launchDate;

}