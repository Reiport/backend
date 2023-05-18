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

import backend.backend.application.services.RequestService;
import backend.backend.application.services.client.PayRequestService;
import backend.backend.application.services.driver.FinishDeliver;
import backend.backend.application.services.driver.AcceptDeliver;
import backend.backend.application.services.request.CompleteRequestService;
import backend.backend.application.services.request.CreateSuspendedRequest;
import backend.backend.application.services.worker.manager.AproveRequestService;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;
import backend.backend.presentation.contracts.request.ContentRequest;
import backend.backend.presentation.contracts.request.RequestResponse;
import backend.backend.presentation.contracts.worker.DriverResponse;
import backend.backend.presentation.mappers.GuestMapper;
import backend.backend.presentation.mappers.RequestMapper;
import jakarta.validation.Valid;

@RequestMapping("/requests")
@RestController
public class RequestController {

        @Autowired
        private RequestService requestService;

        @Autowired
        private CreateSuspendedRequest createSuspendedRequest;

        @Autowired
        private AproveRequestService aproveRequestService;

        @Autowired
        private AcceptDeliver startDeliver;

        @Autowired
        private FinishDeliver finishDeliver;

        @Autowired
        private CompleteRequestService completeRequestService;

        @Autowired
        private PayRequestService payRequestService;

        @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor')")
        @GetMapping("/")
        public ResponseEntity<Collection<RequestResponse>> getAllRequests() {

                Collection<RequestInfo> result = this.requestService.getAllRequests();

                var response = result
                                .stream()
                                .map(request -> RequestMapper.INSTANCE.toRequestInfoResponse(request))
                                .collect(Collectors.toList());

                return ResponseEntity
                                .ok()
                                .body(response);

        }

        @PreAuthorize("hasAuthority('Rececionista')")
        @GetMapping("/client")
        public ResponseEntity<Collection<RequestResponse>> getAllRequestFromClient(@RequestParam int id) {

                Collection<RequestInfo> result = this.requestService.getAllRequestOfClient(id);

                var response = result
                                .stream()
                                .map(request -> RequestMapper.INSTANCE.toRequestInfoResponse(request))
                                .collect(Collectors.toList());

                return ResponseEntity
                                .ok()
                                .body(response);

        }

        @PreAuthorize("hasAuthority('Gestor')")
        @GetMapping("/drivers")
        public ResponseEntity<Collection<DriverResponse>> getAllDriversFromRequest(@RequestParam int id) {

                Collection<Driver> result = this.requestService.getAllDrivers(id);

                var response = result
                                .stream()
                                .map(request -> GuestMapper.INSTANCE.toDriverResponse(request))
                                .collect(Collectors.toList());

                return ResponseEntity
                                .ok()
                                .body(response);

        }

        @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor')")
        @GetMapping("")
        public ResponseEntity<RequestResponse> getRequest(@RequestParam int id) {

                var result = this.requestService.getRequest(id);

                return ResponseEntity
                                .ok()
                                .body(RequestMapper.INSTANCE.toRequestResponse(result));

        }

        @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor')")
        @GetMapping("/members")
        public ResponseEntity<?> getRequestMembers(@RequestParam int id) {

                Collection<Guest> members = requestService.getRequestMembers(id);

                var result = members.stream()
                                .map(guest -> GuestMapper.INSTANCE.toWorkerResponse(guest))
                                .collect(Collectors.toList());

                return ResponseEntity
                                .ok()
                                .body(result);

        }

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
        public ResponseEntity<String> handleRequest(@Valid @RequestBody ContentCompleteRequest request) {

                this.aproveRequestService.handle(request);

                return ResponseEntity
                                .ok()
                                .body("O pedido foi enviado para o condutor principal");

        }

        @PreAuthorize("hasAuthority('Motorista')")
        @PostMapping("/accept")
        public ResponseEntity<?> acceptDelivery(@RequestParam int id) {

                this.startDeliver.handle(id);

                return ResponseEntity
                                .ok()
                                .body("O pedido foi aceite, e em procedimento!");

        }

        @PreAuthorize("hasAuthority('Motorista')")
        @PostMapping("/finish")
        public ResponseEntity<?> finishDelivery(@RequestParam int id) {

                this.finishDeliver.handle(id);

                return ResponseEntity
                                .ok()
                                .body("O pedido foi completo, aguarda dados de pagamento!");

        }

        @PreAuthorize("hasAuthority('Rececionista')")
        @PostMapping("/complete")
        public ResponseEntity<?> completeDelivery(@RequestParam int id) {

                this.completeRequestService.handle(id);

                return ResponseEntity
                                .ok()
                                .body("O pedido foi pago com sucesso!");

        }

        @PreAuthorize("hasAuthority('Cliente')")
        @PostMapping("/payment")
        public ResponseEntity<?> payDelivery(@RequestParam int id) {

                this.payRequestService.handle(id);

                return ResponseEntity
                                .ok()
                                .body("O pedido foi pago com sucesso!");

        }

}
