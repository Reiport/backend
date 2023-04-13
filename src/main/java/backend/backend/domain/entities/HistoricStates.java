package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "historicstate")
@Data
@Entity
public class HistoricStates {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate startDate = LocalDate.now();

    @Column(nullable = false)
    private State state;

    @Column
    private LocalDate createdAt = LocalDate.now();

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    public HistoricStates(State state, Request request, Guest guest) {
        this.state = state;
        this.request = request;
        this.guest = guest;

    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request", nullable = false)
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest", nullable = false)
    private Guest guest;

}
