package backend.backend.application.common.interfaces.repositories;

import java.util.Optional;

import backend.backend.domain.entities.Container;

public interface IContainerRepository {

    Optional<Container> getContainerById(String license);

}
