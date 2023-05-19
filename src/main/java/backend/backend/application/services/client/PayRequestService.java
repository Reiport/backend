package backend.backend.application.services.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.services.RequestService;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.errors.request.RequestStateViolated;

@Service
public class PayRequestService {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private RequestService requestService;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IMailSender mailSender;

    public void handle(int requestId) {

        Guest workingUser = authorizationFacade.getAuthenticatedUser();
        Request workingRequest = requestService.getRequest(requestId);

        if (requestService.getState(workingRequest) != State.COMPLETED)
            throw new RequestStateViolated();

        // Check Request
        // Verify How does the Client Pay? Now, or ...

        requestRepository.changeState(workingRequest, State.PAYED, workingUser);

        Map<String, Object> data = new HashMap<>();
        data.put("name", workingUser.getFirstName() + " "
                + workingUser.getLastName());
        data.put("request", workingRequest.getId());

        mailSender.sendEmail(
                "Confirmação de Pagamento",
                workingUser.getEmail(),
                "invoice",
                data);

    }

}
