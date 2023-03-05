package backend.backend.application.common.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backend.backend.domain.entities.Guest;

@Repository
public interface IUserRepository extends JpaRepository<Guest, Integer> {

    @Query(value = "SELECT * FROM Guest u WHERE u.email = ?1", nativeQuery = true)
    Optional<Guest> findByEmail(String email);

    Optional<Guest> findById(Integer id);

}