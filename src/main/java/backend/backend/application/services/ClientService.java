package backend.backend.application.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterRequest;

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

    public void updateClient(int id, RegisterRequest request) {

        Guest client = getClientById(id);

        if (client == null) {
            throw new RuntimeException("Não existe nenhum cliente com este id");
        }

        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setBirthDate(LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        client.setPort(request.getPort());
        client.setStreet(request.getStreet());
        client.setTelephone(request.getTelephone());

        client.setUpdatedAt(LocalDate.now());
        this.userRepository.save(client);

    }

}
