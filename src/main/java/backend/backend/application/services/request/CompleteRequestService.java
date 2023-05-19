package backend.backend.application.services.request;

import java.math.BigDecimal;
import java.time.LocalDate;
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

        if (requestService.getState(workingRequest) != State.DELIVERED) {
            throw new RequestStateViolated();
        }

        requestRepository.changeState(workingRequest, State.COMPLETED, authorizationFacade.getAuthenticatedUser());

        Map<String, Object> data = new HashMap<>();
        data.put("name", client.getFirstName() + " "
                + client.getLastName());
        data.put("street", client.getStreet());
        data.put("port", client.getPort());
        data.put("postalCode", client.getPostalCode().getId());
        data.put("locality", client.getPostalCode().getLocality());
        data.put("emissionDate", LocalDate.now());
        data.put("limitDate", LocalDate.now().plusDays(365));
        data.put("request", workingRequest.getId());
        data.put("clientId", client.getId());
        data.put("nif", client.getNif());
        data.put("qtCargo", workingRequest.getCargoWeight());
        data.put("value", workingRequest.getDeliveryPrice());
        data.put("ivaValue", workingRequest.getDeliveryPrice().multiply(BigDecimal.valueOf(0.23)));
        data.put("totalValue", workingRequest.getDeliveryPrice()
                .add(workingRequest.getDeliveryPrice().multiply(BigDecimal.valueOf(0.23))));

        mailSender.sendEmail(
                "Fatura Pagamento",
                client.getEmail(),
                "invoiceTemplate",
                data);

    }

}
