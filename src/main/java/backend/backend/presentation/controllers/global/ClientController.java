package backend.backend.presentation.controllers.global;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.client.GetAllClientsService;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.worker.WorkerResponse;
import backend.backend.presentation.mappers.GuestMapper;

@RequestMapping("/clients")
@RestController
public class ClientController {

    @Autowired
    private GetAllClientsService getAllClientsService;

    @GetMapping("/")
    private ResponseEntity<Collection<WorkerResponse>> getAllClients() {

        Collection<Guest> clients = getAllClientsService.handle(10);

        var response = clients
                .stream()
                .map(guest -> GuestMapper.INSTANCE.toWorkerResponse(guest))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(response);

    }

}
