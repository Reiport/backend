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

import java.time.LocalDate;
import java.util.Set;

@Table(name = "guesttype")
@Data
@Entity
@NoArgsConstructor
public class GuestType {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String description;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    public GuestType(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "guestType")
    private Set<Guest> guestTypeGuests;

}
