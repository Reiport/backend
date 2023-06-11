package backend.backend.application.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterWorkerRequest;

@Service
public class WorkerService {

    @Autowired
    private IUserRepository userRepository;

    public Collection<Guest> getAllWorker(int id) {
        return this.userRepository.getAllWorkers();
    }

    public Guest getWorker(int id) {
        return this.userRepository.findWorkerById(id);
    }

    public void deleteWorkerById(int id) {
        Guest worker = getWorker(id);

        if (worker == null) {
            throw new RuntimeException("Não existe nehum trabalhador com esse id");
        }

        worker.setDeletedAt(LocalDate.now());
        this.userRepository.save(worker);
    }

    public void updateWorkerById(int id, RegisterWorkerRequest request) {
        Guest worker = getWorker(id);

        if (worker == null) {
            throw new RuntimeException("Não existe nehum trabalhador com esse id");
        }

        worker.setFirstName(request.getFirstName());
        worker.setLastName(request.getLastName());
        worker.setBirthDate(LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        worker.setPort(request.getPort());
        worker.setStreet(request.getStreet());
        worker.setTelephone(request.getTelephone());

        worker.setUpdatedAt(LocalDate.now());
        this.userRepository.save(worker);

    }

}
