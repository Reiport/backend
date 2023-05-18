package backend.backend.presentation.contracts.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvoiceResponse {
    private Integer id;
    private BigDecimal priceWithoutVat;
    private BigDecimal priceWithVat;
    private LocalDate dateIssue;
    private LocalDate paymentDate;
}
