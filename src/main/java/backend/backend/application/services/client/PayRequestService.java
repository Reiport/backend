package backend.backend.application.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.services.request.GetRequestService;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;

@Service
public class PayRequestService {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IMailSender mailSender;

    public void handle(int requestId) {

        // Check Request
        // Verify How does the Client Pay? Now, or ...
        // How to actually pay

        Request workingRequest = getRequestService.handle(requestId);
        requestRepository.changeState(workingRequest, State.PAYED, authorizationFacade.getAuthenticatedUser());

        mailSender.sendEmail(
                "Confirmação de Pagamento",
                authorizationFacade.getAuthenticatedUser().getEmail(),
                "requestAproved",
                null);

    }

}
