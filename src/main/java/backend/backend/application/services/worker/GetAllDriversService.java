package backend.backend.application.services.worker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Driver;

@Service
public class GetAllDriversService {

    @Autowired
    private IUserRepository userRepository;

    public Collection<Driver> handle() {
        return userRepository.getFullDrivers();
    }

}
