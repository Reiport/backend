package backend.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "guest")
@Data
@Entity
@NoArgsConstructor
public class Guest implements UserDetails {

    // private final Collection<GuestType> role = new ArrayList<>();

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200, unique = true)
    private String email;

    @Column(nullable = false, length = 100, name = "passwd")
    private String password;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private String nif;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false)
    private Integer port;

    @Column(nullable = false, precision = 10, scale = 2)
    private String telephone;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code", nullable = false)
    private PostalCode postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_type", nullable = false)
    private GuestType guestType;

    @OneToMany(mappedBy = "id")
    private Set<Driver> idDrivers;

    @OneToMany(mappedBy = "client")
    private Set<Request> clientRequests;

    @OneToMany(mappedBy = "guest")
    private Set<HistoricStates> guestHistoricStatess;

    public Guest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Guest(String email, String password, String firstName, String lastName, LocalDate birthDate, String nif,
            String street, Integer port, String telephone, PostalCode postalCode, GuestType guestType) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nif = nif;
        this.street = street;
        this.port = port;
        this.telephone = telephone;
        this.postalCode = postalCode;
        this.guestType = guestType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(guestType.getName()));

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
