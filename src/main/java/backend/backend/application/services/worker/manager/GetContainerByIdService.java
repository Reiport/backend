package backend.backend.application.services.worker.manager;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IContainerRepository;
import backend.backend.domain.entities.Container;

@Service
public class GetContainerByIdService {

    @Autowired
    private IContainerRepository _containerRepository;

    public Container handle(String lincense) {

        Optional<Container> container = this._containerRepository.getContainerById(lincense);

        if (container.isEmpty())
            throw new RuntimeException("Was not Found any container with that license!");

        return container.get();

    }

}
