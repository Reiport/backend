package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "invoice")
@Data
@Entity
@NoArgsConstructor
public class Invoice {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal priceWithoutVat;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal priceWithVat;

    @Column(nullable = false)
    private LocalDate dateIssue;

    @Column
    private LocalDate paymentDate;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @OneToMany(mappedBy = "invoice")
    private Set<Request> invoiceRequests;

}
