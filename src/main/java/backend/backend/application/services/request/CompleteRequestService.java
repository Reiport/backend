package backend.backend.application.services.request;

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
import jakarta.transaction.Transactional;

@Service
public class CompleteRequestService {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private RequestService requestService;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IMailSender mailSender;

    @Transactional
    public void handle(int id) {

        Guest client;

        Request workingRequest = requestService.getRequest(id);

        client = requestRepository.getClient(workingRequest);

        if (requestService.getState(workingRequest) != State.EXECUTION) {
            throw new RequestStateViolated();
        }

        requestRepository.changeState(workingRequest, State.COMPLETED, authorizationFacade.getAuthenticatedUser());

        Map<String, Object> map = new HashMap<>();
        map.put("to", "5091");

        mailSender.sendEmail(
                "Test",
                client.getEmail(),
                "invoiceTemplate",
                map);

        mailSender.sendEmail(
                "Fatura Pagamento",
                client.getEmail(),
                "driverNewRequest",
                null);

    }

}