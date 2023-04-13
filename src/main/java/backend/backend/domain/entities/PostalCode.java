package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "postalcode")
@Data
@Entity
@NoArgsConstructor
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

}
