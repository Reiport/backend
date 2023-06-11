package backend.backend.presentation.contracts.info;

public class CountryResponse {
    private String country;

    public CountryResponse() {
    }

    public CountryResponse(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
