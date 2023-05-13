package backend.backend.application.services.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.services.request.GetRequestService;
import backend.backend.domain.entities.Request;

@Service
public class FinishDeliver {

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IMailSender mailSender;

    public void handle(int requestId) {

        // TODO: Send Guias

        Request workingRequest = getRequestService.handle(requestId);

        mailSender.sendEmail(
                "Entrega Concluída",
                requestRepository.getClient(workingRequest).get().getEmail(),
                "requestAproved",
                null);

    }

}
