package backend.backend.application.services.driver;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IDriverRepository;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.RequestService;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.errors.request.RequestStateViolated;

@Service
public class FinishDeliver {

    @Autowired
    private RequestService requestService;

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IMailSender mailSender;

    public void handle(int requestId) {

        Guest client;

        Request workingRequest = requestService.getRequest(requestId);

        client = requestRepository.getClient(workingRequest);

        if (requestService.getState(workingRequest) != State.EXECUTION)
            throw new RequestStateViolated();

        requestRepository.changeState(workingRequest, State.DELIVERED, authorizationFacade.getAuthenticatedUser());

        Driver driver = userRepository.getDriver(authorizationFacade.getAuthenticatedUser().getId());
        driver.setIsWorking(false);
        driverRepository.save(driver);

        Map<String, Object> data = new HashMap<>();
        data.put("name", client.getFirstName() + " "
                + client.getLastName());
        data.put("request", workingRequest.getId());

        mailSender.sendEmail(
                "Entrega Concluída",
                client.getEmail(),
                "finishDeliver",
                data);

    }

}
