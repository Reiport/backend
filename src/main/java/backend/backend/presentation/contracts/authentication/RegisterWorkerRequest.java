package backend.backend.presentation.contracts.authentication;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterWorkerRequest {

    @NotEmpty(message = "Por favor preencha o campo: nome própio")
    private String firstName;

    @NotEmpty(message = "Por favor preencha o campo: último nome")
    private String lastName;

    @NotEmpty(message = "Por favor preencha o campo: data de nascimento")
    private String birthDate;

    @Size(min = 9, max = 9, message = "Introduza o nif válido com 9 caracteres")
    @NotEmpty(message = "Por favor preencha o campo: nif")
    private String nif;

    @NotEmpty(message = "Por favor preencha o campo: rua")
    private String street;

    @NotNull(message = "Por favor preencha o campo: número da porta")
    private Integer port;

    @Size(min = 9, max = 9, message = "Introduza um número de telefone válido com 9 caracteres")
    @NotEmpty(message = "Por favor preencha o campo: telefone")
    private String telephone;

    @Size(min = 8, max = 8, message = "Introduza um codigo postal válido com 8 caracteres")
    @NotEmpty(message = "Por favor preencha o campo: codigo postal")
    private String postalCode;

    @NotEmpty(message = "Por favor preencha o campo: tipo de funcionario")
    private String guestType;

    public RegisterWorkerRequest() {
    }

    public RegisterWorkerRequest(String firstName, String lastName, String birthDate, String nif, String street,
            Integer port, String telephone, String postalCode, String guestType) {
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
