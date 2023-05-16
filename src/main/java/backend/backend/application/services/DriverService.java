package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Driver;

@Service
public class DriverService {

    @Autowired
    private IUserRepository userRepository;

    public Collection<Driver> getAllDrivers() {
        return userRepository.getFullDrivers();
    }

}
