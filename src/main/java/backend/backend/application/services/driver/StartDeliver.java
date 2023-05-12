package backend.backend.application.services.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.services.request.GetRequestService;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;

@Service
public class StartDeliver {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private IRequestRepository requestRepository;

    public void handle(int requestId) {
        Request workingRequest = getRequestService.handle(requestId);
        requestRepository.changeState(workingRequest, State.EXECUTION, authorizationFacade.getAuthenticatedUser());
    }

}
