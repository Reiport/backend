package backend.backend.application.common.interfaces.repositories;

import java.util.Collection;

import backend.backend.domain.entities.Container;

public interface IContainerRepository {

    Container getContainerById(String license);

    Collection<Container> getAllContainers();

    Container createContainer(Container container);

    void deleteByLincense(String license);

}
