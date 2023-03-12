package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "guestgroup")
@Data
@Entity
public class GuestGroup {

    @Column
    private LocalDate beginDate;

    @Column
    private LocalDate exitDate;

    @Column
    private LocalDate createdAt;

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
    @JoinColumn(name = "guest", nullable = false)
    private Guest guest;

}
