package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "postalcode")
@Entity

public class PostalCode {

    @Id
    @Column(nullable = false, updatable = false, length = 8)
    private String id;

    @Column(length = 200)
    private String description;

    @Column(nullable = false, length = 100)
    private String locality;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    public PostalCode(String id, String locality, Country country) {
        this.id = id;
        this.locality = locality;
        this.country = country;
    }

    public PostalCode(String id, String description, String locality, Country country) {
        this.id = id;
        this.description = description;
        this.locality = locality;
        this.country = country;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "postalCode")
    private Set<Guest> postalCodeGuests;

    @OneToMany(mappedBy = "postalCodeDest")
    private Set<Request> postalCodeDestRequests;

    @OneToMany(mappedBy = "postalCodeOri")
    private Set<Request> postalCodeOriRequests;

    public PostalCode() {
    }

    public PostalCode(String id, String description, String locality, LocalDate createdAt, LocalDate updatedAt,
            LocalDate deletedAt, Country country, Set<Guest> postalCodeGuests, Set<Request> postalCodeDestRequests,
            Set<Request> postalCodeOriRequests) {
        this.id = id;
        this.description = description;
        this.locality = locality;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.country = country;
        this.postalCodeGuests = postalCodeGuests;
        this.postalCodeDestRequests = postalCodeDestRequests;
        this.postalCodeOriRequests = postalCodeOriRequests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Guest> getPostalCodeGuests() {
        return postalCodeGuests;
    }

    public void setPostalCodeGuests(Set<Guest> postalCodeGuests) {
        this.postalCodeGuests = postalCodeGuests;
    }

    public Set<Request> getPostalCodeDestRequests() {
        return postalCodeDestRequests;
    }

    public void setPostalCodeDestRequests(Set<Request> postalCodeDestRequests) {
        this.postalCodeDestRequests = postalCodeDestRequests;
    }

    public Set<Request> getPostalCodeOriRequests() {
        return postalCodeOriRequests;
    }

    public void setPostalCodeOriRequests(Set<Request> postalCodeOriRequests) {
        this.postalCodeOriRequests = postalCodeOriRequests;
    }

}
