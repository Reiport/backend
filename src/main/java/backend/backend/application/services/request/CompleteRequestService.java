package backend.backend.application.services.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;

@Service
public class CompleteRequestService {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IMailSender mailSender;

    public void handle(int id) {

        // Gera Fatura em via ao cliente
        Request workingRequest = getRequestService.handle(id);
        requestRepository.changeState(workingRequest, State.COMPLETED, authorizationFacade.getAuthenticatedUser());

        mailSender.sendEmail(
                "Fatura Pagamento",
                requestRepository.getClient(workingRequest).get().getEmail(),
                "driverNewRequest",
                null);

    }

}
