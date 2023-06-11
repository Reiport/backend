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

import java.time.LocalDate;

@Table(name = "historicstate")
@Entity
public class HistoricStates {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate startDate = LocalDate.now();

    @Column
    private State state;

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

    public HistoricStates() {
    }

    public HistoricStates(Integer id, LocalDate startDate, State state, LocalDate updatedAt, LocalDate deletedAt,
            Request request, Guest guest) {
        this.id = id;
        this.startDate = startDate;
        this.state = state;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.request = request;
        this.guest = guest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

}
