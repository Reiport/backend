package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IContainerRepository;
import backend.backend.domain.entities.Container;

@Service
public class ContainerService {

    @Autowired
    private IContainerRepository containerRepository;

    public Collection<Container> getAllContainers() {
        return containerRepository.getAllContainers();
    }

    public Container getContainerByLicense(String lincense) {
        return this.containerRepository.getContainerById(lincense);
    }

}
