package backend.backend.presentation.controllers.global;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.ClientService;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.SimpleResponse;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import backend.backend.presentation.contracts.worker.WorkerResponse;
import backend.backend.presentation.mappers.GuestMapper;
import jakarta.validation.Valid;

@RequestMapping("/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Admin')")
    @GetMapping("/")
    public ResponseEntity<Collection<WorkerResponse>> getAllClients() {

        Collection<Guest> clients = clientService.getAllClients();

        var response = clients
                .stream()
                .map(guest -> GuestMapper.INSTANCE.toWorkerResponse(guest))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(response);

    }

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<WorkerResponse> getClientById(@RequestParam int id) {

        Guest response = clientService.getClientById(id);

        return ResponseEntity
                .ok()
                .body(GuestMapper.INSTANCE.toWorkerResponse(response));

    }

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Admin')")
    @PutMapping("/update")
    public ResponseEntity<SimpleResponse> updateClient(@RequestParam int id,
            @Valid @RequestBody RegisterRequest request) {

        clientService.updateClient(id, request);

        return ResponseEntity
                .ok()
                .body(new SimpleResponse("O cliente foi atualizado!"));

    }

}
