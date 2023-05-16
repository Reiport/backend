package backend.backend.application.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend.domain.entities.Model;

@Repository
public interface IModelRepository extends JpaRepository<Model, Integer> {

    Model findModelByName(String name);

}
