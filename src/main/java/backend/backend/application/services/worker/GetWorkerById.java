package backend.backend.application.services.worker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.errors.worker.WorkerNotFoundException;

@Service
public class GetWorkerById {

    @Autowired
    private IUserRepository _userRepository;

    public Guest handle(int id) {

        Optional<Guest> workerFound = this._userRepository.findById(id);

        if (workerFound.isEmpty()) {
            throw new WorkerNotFoundException();
        }

        return workerFound.get();

    }

}
