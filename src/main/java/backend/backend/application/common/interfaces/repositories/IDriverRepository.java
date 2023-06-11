package backend.backend.application.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.domain.entities.Driver;

public interface IDriverRepository extends JpaRepository<Driver, Integer> {

}
