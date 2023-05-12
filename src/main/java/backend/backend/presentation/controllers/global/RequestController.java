package backend.backend.presentation.controllers.global;

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

import backend.backend.application.services.request.CreateSuspendedRequest;
import backend.backend.application.services.request.GetAllRequestService;
import backend.backend.application.services.request.GetRequestMembersService;
import backend.backend.application.services.request.GetRequestService;
import backend.backend.application.services.worker.manager.CompleteRequestService;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;
import backend.backend.presentation.contracts.request.ContentRequest;
import backend.backend.presentation.contracts.request.RequestResponse;
import backend.backend.presentation.mappers.GuestMapper;
import backend.backend.presentation.mappers.RequestMapper;
import jakarta.validation.Valid;

@RequestMapping("/requests")
@RestController
public class RequestController {

    @Autowired
    private GetRequestMembersService getRequestMembersService;

    @Autowired
    private CreateSuspendedRequest createSuspendedRequest;

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private GetAllRequestService getAllRequestService;

    @Autowired
    private CompleteRequestService completeRequestService;

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor')")
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

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor')")
    @GetMapping("")
    public ResponseEntity<RequestResponse> getRequest(@RequestParam int id) {

        var result = this.getRequestService.handle(id);

        return ResponseEntity
                .ok()
                .body(RequestMapper.INSTANCE.toRequestResponse(result));

    }

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor')")
    @GetMapping("/members")
    public ResponseEntity<?> getRequestMembers(@RequestParam int id) {

        Collection<Guest> members = getRequestMembersService.handle(id);

        var result = members.stream()
                .map(guest -> GuestMapper.INSTANCE.toWorkerResponse(guest))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(result);

    }

    // TODO: Alterar status response to CREATED
    @PreAuthorize("hasAuthority('Rececionista')")
    @PostMapping("/create")
    public ResponseEntity<String> createRequest(@Valid @RequestBody ContentRequest request) {

        this.createSuspendedRequest.handle(request);

        return ResponseEntity
                .ok()
                .body("Request was submited to validation");

    }

    @PreAuthorize("hasAuthority('Gestor')")
    @PostMapping("/handle")
    public ResponseEntity<?> handleRequest(@Valid @RequestBody ContentCompleteRequest request) {

        this.completeRequestService.handle(request);

        return ResponseEntity
                .ok()
                .body("O pedido foi enviado para o condutor principal");

    }

}
