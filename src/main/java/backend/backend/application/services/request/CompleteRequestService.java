package backend.backend.application.services.request;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.IPDFGenerator;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.services.RequestService;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;

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

    @Autowired
    private IPDFGenerator pdfGenerator;

    public void handle(int id) {

        // Gera Fatura em via ao cliente
        Request workingRequest = requestService.getRequest(id);
        requestRepository.changeState(workingRequest, State.COMPLETED, authorizationFacade.getAuthenticatedUser());

        Map<String, Object> map = new HashMap<>();
        map.put("to", "5091");

        mailSender.sendEmail(
                "Test",
                requestRepository.getClient(workingRequest).get().getEmail(),
                "invoiceTemplate",
                map);

        pdfGenerator.generatePDF();

        mailSender.sendEmail(
                "Fatura Pagamento",
                requestRepository.getClient(workingRequest).get().getEmail(),
                "driverNewRequest",
                null);

    }

}
