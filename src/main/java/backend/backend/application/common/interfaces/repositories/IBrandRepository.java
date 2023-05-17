package backend.backend.application.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.domain.entities.Brand;

public interface IBrandRepository extends JpaRepository<Brand, Integer> {

}
