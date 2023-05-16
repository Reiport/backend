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
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;
import backend.backend.presentation.errors.request.RequestAlreadyCompletedException;
import jakarta.transaction.Transactional;

@Service
public class AproveRequestService {

    // TODO: Change how do i get drivers
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

        // TODO: Verify is Drivers are in work

        // Get Request
        Request workingRequest = requestService.getRequest(request.getRequestId());

        if (_requestRepository.getRequestState(workingRequest) == State.SCHEDULED)
            throw new RequestAlreadyCompletedException();

        // Select Vehicles from Manager or Has Client
        if (!request.isHasClientContainer())
            workingRequest.setContainerLicense(containerService.getContainerByLicense(request.getContainerLicense()));

        // Select Containers from Manager or Has Client
        if (!request.isHasClientTruck())
            workingRequest.setLicense(truckService.getTruckById(request.getVehicleLicense()));

        // Alterar o estado do request
        _requestRepository.changeState(workingRequest, State.SCHEDULED, authorizationFacade.getAuthenticatedUser());

        // Set driver or Drivers to request
        _requestRepository.addDriver(workingRequest, userRepository.getDriver(request.getDriverId()).get(),
                request.getKilometers());

        if (request.isOptionalDriver())
            _requestRepository.addDriver(workingRequest, userRepository.getDriver(request.getOptionalDriverId()).get(),
                    request.getKilometers());

        // Send it to principal driver
        // Send Email with the information of request, explaining what to do to the
        // driver
        mailSender.sendEmail(
                "Novo pedido de entrega",
                userRepository.findById(request.getDriverId()).get().getEmail(),
                "driverNewRequest",
                null);

        // Send Email with the change of state, explaining that the request was aproved
        mailSender.sendEmail(
                "Pedido Atualizado",
                _requestRepository.getClient(workingRequest).get().getEmail(),
                "requestAproved",
                null);

    }

}
