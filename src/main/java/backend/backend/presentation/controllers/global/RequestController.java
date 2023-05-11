package backend.backend.presentation.controllers.global;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.request.CreateRequestService;
import backend.backend.application.services.request.GetAllRequestService;
import backend.backend.application.services.request.GetRequestService;
import backend.backend.application.services.worker.manager.CompleteRequestService;
import backend.backend.domain.entities.Request;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;
import backend.backend.presentation.contracts.request.ContentRequest;
import backend.backend.presentation.contracts.request.RequestResponse;
import backend.backend.presentation.mappers.RequestMapper;
import jakarta.validation.Valid;

@RequestMapping("/requests")
@RestController
public class RequestController {

    @Autowired
    private CreateRequestService createRequestService;

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private GetAllRequestService getAllRequestService;

    @Autowired
    private CompleteRequestService completeRequestService;

    @GetMapping("/")
    public ResponseEntity<Collection<RequestResponse>> getAllRequests() {

        Collection<Request> result = this.getAllRequestService.handle();

        var response = result
                .stream()
                .map(request -> RequestMapper.INSTANCE.toRequestResponse(request))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(response);

    }

    @GetMapping("")
    public ResponseEntity<RequestResponse> getRequest(@RequestParam int id) {

        var result = this.getRequestService.handle(id);

        return ResponseEntity
                .ok()
                .body(RequestMapper.INSTANCE.toRequestResponse(result));

    }

    // TODO: Alterar status response to CREATED
    @PostMapping("/create")
    private ResponseEntity<?> createRequest(@Valid @RequestBody ContentRequest request) {

        this.createRequestService.handle(request);

        return ResponseEntity
                .ok()
                .body("Request was submited to validation");

    }

    @PostMapping("/handle")
    private ResponseEntity<?> handleRequest(@Valid @RequestBody ContentCompleteRequest request) {

        this.completeRequestService.handle(request);

        return ResponseEntity
                .ok()
                .body("Request was submited to validation");

    }

}
