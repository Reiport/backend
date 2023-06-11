package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Table(name = "guestgroup")
@Entity
public class GuestGroup {

    @Column
    private LocalDate beginDate = LocalDate.now();

    @Column
    private LocalDate exitDate;

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
    @JoinColumn(name = "guest", nullable = false)
    private Guest guest;

    public GuestGroup() {
    }

    public GuestGroup(LocalDate beginDate, LocalDate exitDate, LocalDate createdAt, LocalDate updatedAt,
            LocalDate deletedAt, Request request, Guest guest) {
        this.beginDate = beginDate;
        this.exitDate = exitDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.request = request;
        this.guest = guest;
    }

    public GuestGroup(Request request, Guest guest) {
        this.request = request;
        this.guest = guest;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
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
