package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;

@Service
public class ClientService {

    @Autowired
    private IUserRepository userRepository;

    public Collection<Guest> getAllClients() {
        return userRepository.getAllClients();
    }

    public Guest getClientById(int id) {
        return userRepository.findClientById(id);
    }

}
