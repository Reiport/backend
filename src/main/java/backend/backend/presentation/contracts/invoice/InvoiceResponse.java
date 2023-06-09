package backend.backend.presentation.contracts.invoice;

import java.math.BigDecimal;

public class InvoiceResponse {

    private Integer id;
    private BigDecimal priceWithoutVat;
    private BigDecimal priceWithVat;
    private String dateIssue;
    private String nif;
    private String street;
    private int port;
    private String postalCode;
    private String paymentDate;

    public InvoiceResponse() {
    }

    public InvoiceResponse(Integer id, BigDecimal priceWithoutVat, BigDecimal priceWithVat, String dateIssue,
            String nif, String street, int port, String postalCode, String paymentDate) {
        this.id = id;
        this.priceWithoutVat = priceWithoutVat;
        this.priceWithVat = priceWithVat;
        this.dateIssue = dateIssue;
        this.nif = nif;
        this.street = street;
        this.port = port;
        this.postalCode = postalCode;
        this.paymentDate = paymentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPriceWithoutVat() {
        return priceWithoutVat;
    }

    public void setPriceWithoutVat(BigDecimal priceWithoutVat) {
        this.priceWithoutVat = priceWithoutVat;
    }

    public BigDecimal getPriceWithVat() {
        return priceWithVat;
    }

    public void setPriceWithVat(BigDecimal priceWithVat) {
        this.priceWithVat = priceWithVat;
    }

    public String getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

}
