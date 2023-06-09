package backend.backend.application.services.client;

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
import backend.backend.domain.entities.Invoice;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.contracts.PayRequest;
import backend.backend.presentation.errors.request.RequestStateViolated;
import jakarta.transaction.Transactional;

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

    @Transactional
    public void handle(PayRequest request) {

        Guest workingUser = authorizationFacade.getAuthenticatedUser();
        Request workingRequest = requestService.getRequest(request.getRequestId());

        if (requestService.getState(workingRequest) != State.COMPLETED)
            throw new RequestStateViolated();

        Invoice invoice = requestRepository.getInvoice(request.getInvoiceId());

        if (request.getAmount() != invoice.getPriceWithVat().doubleValue()) {
            throw new RuntimeException("Pagamento não admitido!. Porfavor introduza o valor correto");
        }

        invoice.setPaymentDate(LocalDate.now());
        requestRepository.updateInvoice(invoice);

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
