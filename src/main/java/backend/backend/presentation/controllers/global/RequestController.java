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
import backend.backend.domain.entities.Container;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Invoice;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.domain.entities.Statistics;
import backend.backend.domain.entities.Vehicle;
import backend.backend.presentation.contracts.PayRequest;
import backend.backend.presentation.contracts.SimpleResponse;
import backend.backend.presentation.contracts.container.ContainerResponse;
import backend.backend.presentation.contracts.invoice.InvoiceResponse;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;
import backend.backend.presentation.contracts.request.ContentRequest;
import backend.backend.presentation.contracts.request.RequestResponse;
import backend.backend.presentation.contracts.vehicle.TruckResponse;
import backend.backend.presentation.contracts.worker.DriverResponse;
import backend.backend.presentation.mappers.ContainerMapper;
import backend.backend.presentation.mappers.GuestMapper;
import backend.backend.presentation.mappers.RequestMapper;
import backend.backend.presentation.mappers.TruckMapper;
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

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Cliente')")
    @GetMapping("/invoices")
    public ResponseEntity<Collection<InvoiceResponse>> getAllInvoices(@RequestParam int id) {

        Collection<Invoice> result = this.requestService.getAllInvoices(id);

        var response = result
                .stream()
                .map(request -> RequestMapper.INSTANCE.toInvoiceResponse(request))
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

    @PreAuthorize("hasAuthority('Cliente')")
    @GetMapping("/my")
    public ResponseEntity<Collection<RequestResponse>> getAllRequestFromClient() {

        Collection<RequestInfo> result = this.requestService.getAllMyRequests();

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

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping("/vehicle")
    public ResponseEntity<TruckResponse> getVehicleFromRequest(@RequestParam Integer id) {

        Vehicle vehicle = this.requestService.getVehicle(id);

        return ResponseEntity
                .ok()
                .body(TruckMapper.INSTANCE.ToTruckResponse(vehicle));

    }

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping("/container")
    public ResponseEntity<ContainerResponse> getContainerFromRequest(@RequestParam Integer id) {

        Container container = this.requestService.getContainer(id);

        return ResponseEntity
                .ok()
                .body(ContainerMapper.INSTANCE.toContainerResponse(container));

    }

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor') or hasAuthority('Cliente') or hasAuthority('Motorista')")
    @GetMapping("")
    public ResponseEntity<RequestResponse> getRequest(@RequestParam int id) {

        var result = this.requestService.getRequestInfo(id);

        return ResponseEntity
                .ok()
                .body(RequestMapper.INSTANCE.toRequestInfoResponse(result));

    }

    @PreAuthorize("hasAuthority('Motorista')")
    @GetMapping("/deliver/schedual")
    public ResponseEntity<Collection<RequestResponse>> getSchedualDelivers() {

        var result = this.requestService.getSchedualDelivers();

        return ResponseEntity
                .ok()
                .body(result
                        .stream()
                        .map(request -> RequestMapper.INSTANCE.toRequestInfoResponse(request))
                        .collect(Collectors.toList()));

    }

    @PreAuthorize("hasAuthority('Motorista')")
    @GetMapping("/deliver/current")
    public ResponseEntity<RequestResponse> getCurrentDeliver() {

        var result = this.requestService.getCurrentExecuteDeliver();

        return ResponseEntity
                .ok()
                .body(RequestMapper.INSTANCE.toRequestInfoResponse(result));

    }

    @PreAuthorize("hasAuthority('Rececionista') or hasAuthority('Gestor') or hasAuthority('Cliente') or hasAuthority('Motorista')")
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
    public ResponseEntity<SimpleResponse> createRequest(@Valid @RequestBody ContentRequest request) {

        this.createSuspendedRequest.handle(request);

        return ResponseEntity
                .ok()
                .body(new SimpleResponse("O pedido foi submetido para avaliação"));

    }

    @PreAuthorize("hasAuthority('Gestor')")
    @PostMapping("/handle")
    public ResponseEntity<SimpleResponse> handleRequest(@Valid @RequestBody ContentCompleteRequest request) {

        this.aproveRequestService.handle(request);

        return ResponseEntity
                .ok()
                .body(new SimpleResponse("O pedido foi enviado para o condutor principal"));

    }

    @PreAuthorize("hasAuthority('Motorista')")
    @PostMapping("/accept")
    public ResponseEntity<SimpleResponse> acceptDelivery(@RequestParam int id) {

        this.startDeliver.handle(id);

        return ResponseEntity
                .ok()
                .body(new SimpleResponse("O pedido foi aceite, encontra-se em procedimento!"));

    }

    @PreAuthorize("hasAuthority('Motorista')")
    @PostMapping("/finish")
    public ResponseEntity<SimpleResponse> finishDelivery(@RequestParam int id) {

        this.finishDeliver.handle(id);

        return ResponseEntity
                .ok()
                .body(new SimpleResponse("A entrega do pedido foi completa"));

    }

    @PreAuthorize("hasAuthority('Rececionista')")
    @PostMapping("/complete")
    public ResponseEntity<SimpleResponse> completeDelivery(@RequestParam int id) {

        this.completeRequestService.handle(id);

        return ResponseEntity
                .ok()
                .body(new SimpleResponse("O pedido foi finalizado, espera-se dados de pagamento"));

    }

    @PreAuthorize("hasAuthority('Cliente')")
    @PostMapping("/payment")
    public ResponseEntity<SimpleResponse> payDelivery(@Valid @RequestBody PayRequest request) {

        this.payRequestService.handle(request);

        return ResponseEntity
                .ok()
                .body(new SimpleResponse("O pedido foi pago com sucesso!"));

    }

    @PreAuthorize("hasAuthority('Cliente')")
    @GetMapping("/statistics")
    public ResponseEntity<Statistics> statistics() {

        Statistics statistics = this.requestService.getStatisticsFromRequest();

        return ResponseEntity
                .ok()
                .body(statistics);

    }

    @PreAuthorize("hasAuthority('Motorista')")
    @GetMapping("/deliver")
    public ResponseEntity<Collection<RequestResponse>> getRequestToDeliver() {

        Collection<RequestInfo> requestToDeliver = this.requestService.getRequestToDeliver();

        return ResponseEntity
                .ok()
                .body(requestToDeliver
                        .stream()
                        .map(request -> RequestMapper.INSTANCE.toRequestInfoResponse(request))
                        .collect(Collectors.toList()));

    }

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping("/evaluate")
    public ResponseEntity<Collection<RequestResponse>> requestToEvaluate() {

        Collection<RequestInfo> result = this.requestService.getRequestToEvaluate();

        return ResponseEntity
                .ok()
                .body(result
                        .stream()
                        .map(request -> RequestMapper.INSTANCE.toRequestInfoResponse(request))
                        .collect(Collectors.toList()));

    }

}
