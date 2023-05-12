package backend.backend.application.services.worker.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.request.GetRequestService;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;
import backend.backend.presentation.errors.request.RequestAlreadyCompletedException;
import jakarta.transaction.Transactional;

@Service
public class CompleteRequestService {

    // TODO: Change how do i get drivers
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private GetTruckByIdService getTruckByIdService;

    @Autowired
    private GetContainerByIdService getContainerByIdService;

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private IRequestRepository _requestRepository;

    // TODO: Implement an middleware that verifies in each route that user can mess
    // around in the request
    // TODO: Is missing the second container license
    @Transactional
    public void handle(ContentCompleteRequest request) {

        // TODO: Verify is Drivers are in work

        // Get Request
        Request workingRequest = getRequestService.handle(request.getRequestId());

        if (_requestRepository.getRequestState(workingRequest) == State.SCHEDULED)
            throw new RequestAlreadyCompletedException();

        // Select Vehicles from Manager or Has Client
        if (!request.isHasClientContainer())
            workingRequest.setContainerLicense(getContainerByIdService.handle(request.getContainerLicense()));

        // Select Containers from Manager or Has Client
        if (!request.isHasClientTruck())
            workingRequest.setLicense(getTruckByIdService.handle(request.getVehicleLicense()));

        // Alterar o estado do request
        _requestRepository.changeState(workingRequest, State.SCHEDULED);

        // Set driver or Drivers to request
        _requestRepository.addDriver(workingRequest, userRepository.getDriver(request.getDriverId()).get(),
                request.getKilometers());

        if (request.isOptionalDriver())
            _requestRepository.addDriver(workingRequest, userRepository.getDriver(request.getOptionalDriverId()).get(),
                    request.getKilometers());

        // Send it to principal driver
        // TODO: Is missing send the request to the driver

    }

}
