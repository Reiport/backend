package backend.backend.presentation.controllers.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.request.CreateRequestService;
import backend.backend.presentation.contracts.request.ContentRequest;
import jakarta.validation.Valid;

@RequestMapping("/requests")
@RestController
public class RequestController {

    @Autowired
    private CreateRequestService createRequestService;

    // TODO: Alterar status response to CREATED
    @PostMapping("/create")
    private ResponseEntity<?> createRequest(@Valid @RequestBody ContentRequest request) {

        this.createRequestService.handle(request);

        return ResponseEntity
                .ok()
                .body("Request was submited to validation");

    }

}
