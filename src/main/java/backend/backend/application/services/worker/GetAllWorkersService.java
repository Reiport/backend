package backend.backend.application.services.worker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;

@Service
public class GetAllWorkersService {

    @Autowired
    private IUserRepository _userRepository;

    public Collection<Guest> handle(int qty) {
        return this._userRepository.getAllWorkers();
    }

}
