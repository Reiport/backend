package backend.backend.presentation.contracts.worker;

public class WorkerResponse {

    private int id;

    private String avatar;

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

    public WorkerResponse(int id, String avatar, String email, String firstName, String lastName, String birthDate,
            String nif, String street, Integer port, String telephone, String postalCode, String guestType) {
        this.id = id;
        this.avatar = avatar;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nif = nif;
        this.street = street;
        this.port = port;
        this.telephone = telephone;
        this.postalCode = postalCode;
        this.guestType = guestType;
    }

    public WorkerResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getGuestType() {
        return guestType;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType;
    }

}
