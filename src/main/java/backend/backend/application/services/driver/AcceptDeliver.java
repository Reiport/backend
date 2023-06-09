package backend.backend.application.services.driver;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.services.RequestService;
import backend.backend.config.settings.FrontEndSettings;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.errors.request.RequestStateViolated;

@Service
public class AcceptDeliver {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private RequestService requestService;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IMailSender mailSender;

    @Autowired
    private FrontEndSettings frontEndSettings;

    public void handle(int requestId) {

        Guest client;

        Request workingRequest = requestService.getRequest(requestId);

        client = requestRepository.getClient(workingRequest);

        if (requestService.getState(workingRequest) != State.SCHEDULED)
            throw new RequestStateViolated();

        requestRepository.changeState(workingRequest, State.EXECUTION, authorizationFacade.getAuthenticatedUser());

        Map<String, Object> data = new HashMap<>();
        data.put("name", client.getFirstName() + " "
                + client.getLastName());
        data.put("request", workingRequest.getId());
        data.put("web", frontEndSettings.getUrl() + "/dashboard/send?id=" + requestId);

        mailSender.sendEmail(
                "Pedido Atualizado",
                client.getEmail(),
                "startDeliver",
                data);
    }

}
