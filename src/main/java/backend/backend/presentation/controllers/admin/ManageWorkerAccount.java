package backend.backend.presentation.controllers.admin;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.WorkerService;
import backend.backend.application.services.authentication.RegisterWorkerService;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterWorkerRequest;
import backend.backend.presentation.contracts.worker.WorkerResponse;
import backend.backend.presentation.mappers.GuestMapper;
import jakarta.validation.Valid;

@RequestMapping("/admin")
@RestController
public class ManageWorkerAccount {

    @Autowired
    private RegisterWorkerService registerWorkerService;

    @Autowired
    private WorkerService workerService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/workers/")
    public ResponseEntity<Collection<WorkerResponse>> getAllWorkers() {

        var workers = workerService.getAllWorker(10);

        var response = workers
                .stream()
                .map(guest -> GuestMapper.INSTANCE.toWorkerResponse(guest))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(response);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/workers")
    public ResponseEntity<WorkerResponse> getWorkerById(@RequestParam int id) {

        Guest result = workerService.getWorker(id);

        return ResponseEntity
                .ok()
                .body(GuestMapper.INSTANCE.toWorkerResponse(result));

    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/auth/worker")
    public ResponseEntity<String> registerAlternativeUser(@Valid @RequestBody RegisterWorkerRequest request) {

        registerWorkerService.handle(request);

        return ResponseEntity
                .ok()
                .body("The user was generated!");

    }

}
