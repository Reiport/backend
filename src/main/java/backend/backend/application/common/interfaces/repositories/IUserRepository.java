package backend.backend.application.common.interfaces.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backend.backend.domain.entities.Guest;

@Repository
public interface IUserRepository extends JpaRepository<Guest, Integer> {

    @Query(value = "SELECT * FROM Guest u WHERE u.email = ?1", nativeQuery = true)
    Optional<Guest> findByEmail(String email);

    @Query(value = "SELECT g FROM Guest g INNER JOIN g.guestType gt WHERE gt.name = 'Cliente' AND g.id = ?1")
    Optional<Guest> findClientById(int id);

    @Query(value = "SELECT g FROM Guest g INNER JOIN g.guestType gt WHERE gt.name <> 'Cliente'")
    Collection<Guest> getAllWorkers();

    @Query(value = "SELECT g FROM Guest g INNER JOIN g.guestType gt WHERE gt.name = 'Cliente'")
    Collection<Guest> getAllClients();

    Optional<Guest> findById(Integer id);

}
