package backend.backend.application.services.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.errors.authentication.ClientNotFoundException;

@Service
public class GetClientById {

    @Autowired
    private IUserRepository userRepository;

    public Guest handle(int id) {

        Optional<Guest> userFound = userRepository.findClientById(id);

        if (userFound.isEmpty()) {
            throw new ClientNotFoundException();
        }

        return userFound.get();

    }

}
