package backend.backend.presentation.contracts.info;

import java.time.LocalDate;

public class ModelResponse {

    private String name;
    private LocalDate launchDate;

    public ModelResponse() {
    }

    public ModelResponse(String name, LocalDate launchDate) {
        this.name = name;
        this.launchDate = launchDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

}
