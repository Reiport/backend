package backend.backend.application.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.domain.entities.PostalCode;

public interface IPostalCodeRepository extends JpaRepository<PostalCode, String> {

}
