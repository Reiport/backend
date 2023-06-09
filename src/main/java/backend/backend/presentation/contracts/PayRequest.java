package backend.backend.presentation.contracts;

import jakarta.validation.constraints.NotNull;

public class PayRequest {

    @NotNull(message = "Preencher campo: id do pedido")
    private Integer requestId;

    @NotNull(message = "Preencher campo: id da fatura")
    private Integer invoiceId;

    @NotNull(message = "Preencher campo: valor do pagamento")
    private double amount;

    public PayRequest() {
    }

    public PayRequest(Integer requestId, Integer invoiceId, double amount) {
        this.requestId = requestId;
        this.invoiceId = invoiceId;
        this.amount = amount;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
