package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "drivergroup")
@Data
@Entity
@NoArgsConstructor
public class DriverGroup {

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal kilometers;

    @Column
    private Character type;

    @Column
    private LocalDate createdAt = LocalDate.now();

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request", nullable = false)
    private Request request;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver", nullable = false)
    private Driver driver;

    public DriverGroup(BigDecimal kilometers, Request request, Driver driver) {
        this.kilometers = kilometers;
        this.request = request;
        this.driver = driver;
        this.type = 'p';
    }

}
