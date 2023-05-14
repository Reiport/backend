package backend.backend.application.services.worker.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IContainerRepository;
import backend.backend.domain.entities.Container;

@Service
public class GetAllContainersService {

    @Autowired
    private IContainerRepository containerRepository;

    public Collection<Container> handle() {
        return containerRepository.getAllContainers();
    }

}
