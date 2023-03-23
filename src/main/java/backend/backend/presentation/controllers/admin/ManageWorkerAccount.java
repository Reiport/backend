package backend.backend.presentation.controllers.admin;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.authentication.RegisterWorkerService;
import backend.backend.application.services.worker.GetAllWorkersService;
import backend.backend.presentation.contracts.authentication.RegisterWorkerRequest;
import backend.backend.presentation.contracts.worker.WorkerResponse;
import backend.backend.presentation.mappers.GuestMapper;
import jakarta.validation.Valid;

@RequestMapping("/admin")
@RestController
public class ManageWorkerAccount {

    @Autowired
    RegisterWorkerService registerWorkerService;

    @Autowired
    GetAllWorkersService getAllWorkersService;

    @GetMapping("/workers")
    private ResponseEntity<Collection<WorkerResponse>> getAllWorkers() {

        var workers = getAllWorkersService.handle(10);

        var response = workers
                .stream()
                .map(guest -> GuestMapper.INSTANCE.toWorkerResponse(guest))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(response);

    }

    @PostMapping("/auth/worker")
    public ResponseEntity<String> registerAlternativeUser(@Valid @RequestBody RegisterWorkerRequest request) {

        registerWorkerService.handle(request);

        return ResponseEntity
                .ok()
                .body("The user was generated!");

    }

}