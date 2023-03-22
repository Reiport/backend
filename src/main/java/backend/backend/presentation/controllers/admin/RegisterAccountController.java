package backend.backend.presentation.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.authentication.RegisterWorkerService;
import backend.backend.presentation.contracts.authentication.RegisterWorkerRequest;
import jakarta.validation.Valid;

@RequestMapping("/admin/auth")
@RestController
public class RegisterAccountController {

    @Autowired
    RegisterWorkerService registerWorkerService;

    @PostMapping("/register/worker")
    public ResponseEntity<?> registerAlternativeUser(@Valid @RequestBody RegisterWorkerRequest request) {

        registerWorkerService.handle(request);

        return ResponseEntity
                .ok()
                .body("The user was generated!");

    }

}
