package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IContainerRepository;
import backend.backend.application.common.interfaces.repositories.IModelRepository;
import backend.backend.domain.entities.Container;
import backend.backend.presentation.contracts.container.ContainerRequest;
import backend.backend.presentation.mappers.ContainerMapper;
import jakarta.transaction.Transactional;

@Service
public class ContainerService {

    @Autowired
    private IModelRepository modelRepository;

    @Autowired
    private IContainerRepository containerRepository;

    public Collection<Container> getAllContainers() {
        return containerRepository.getAllContainers();
    }

    public Container getContainerByLicense(String lincense) {
        return this.containerRepository.getContainerById(lincense);
    }

    @Transactional
    public void createContainer(ContainerRequest request) {
        containerRepository.createContainer(
                ContainerMapper.INSTANCE.toContainer(
                        request,
                        modelRepository.findModelByName(request.getModel())));
    }

    @Transactional
    public void deleteContainer(String license) {
        containerRepository.deleteByLincense(license);
    }

}
