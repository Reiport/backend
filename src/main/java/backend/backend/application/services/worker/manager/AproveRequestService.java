package backend.backend.application.services.worker.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.ContainerService;
import backend.backend.application.services.RequestService;
import backend.backend.application.services.TruckService;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;
import backend.backend.presentation.errors.request.RequestStateViolated;
import jakarta.transaction.Transactional;

@Service
public class AproveRequestService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private TruckService truckService;

    @Autowired
    private ContainerService containerService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private IRequestRepository _requestRepository;

    @Autowired
    private IMailSender mailSender;

    // TODO: Implement an middleware that verifies in each route that user can mess
    // around in the request
    // TODO: Is missing the second container license
    @Transactional
    public void handle(ContentCompleteRequest request) {

        Driver primeDriver;
        Driver optionalDriver;
        Request workingRequest = requestService.getRequest(request.getRequestId());

        Guest client = _requestRepository.getClient(workingRequest);

        if (requestService.getState(workingRequest) != State.SUSPENDED)
            throw new RequestStateViolated();

        // Select Vehicles from Manager or Has Client
        if (!request.isHasClientContainer())
            workingRequest.setContainerLicense(containerService.getContainerByLicense(request.getContainerLicense()));

        // Select Containers from Manager or Has Client
        if (!request.isHasClientTruck())
            workingRequest.setLicense(truckService.getTruckById(request.getVehicleLicense()));

        // Alterar o estado do request
        _requestRepository.changeState(workingRequest, State.SCHEDULED, authorizationFacade.getAuthenticatedUser());

        // Set driver or Drivers to request
        primeDriver = userRepository.getDriver(request.getDriverId()).get();

        if (primeDriver.getIsWorking())
            throw new RuntimeException("Não é possivel adicionar o motorista "
                    + primeDriver.getGuest_id().getFirstName() + " porque está atualmente num trabalho");

        _requestRepository.addDriver(workingRequest, primeDriver, request.getKilometers());

        optionalDriver = userRepository.getDriver(request.getOptionalDriverId()).get();

        if (optionalDriver.getIsWorking())
            throw new RuntimeException("Não é possivel adicionar o motorista "
                    + primeDriver.getGuest_id().getFirstName() + " porque está atualmente num trabalho");

        if (request.isOptionalDriver())
            _requestRepository.addDriver(workingRequest, optionalDriver,
                    request.getKilometers());

        // Send it to principal driver
        // Send Email with the information of request, explaining what to do to the
        // driver
        mailSender.sendEmail(
                "Novo pedido de entrega",
                primeDriver.getGuest_id().getEmail(),
                "driverNewRequest",
                null);

        // Send Email with the change of state, explaining that the request was aproved
        mailSender.sendEmail(
                "Pedido Atualizado",
                client.getEmail(),
                "requestAproved",
                null);

    }

}
