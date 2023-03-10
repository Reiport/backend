package backend.backend.application.common.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.domain.entities.GuestType;

public interface IGuestTypeRepository extends JpaRepository<GuestType, Integer> {

    Optional<GuestType> findByName(String name);

}
