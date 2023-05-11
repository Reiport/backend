package backend.backend.application.services.worker.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.services.request.GetRequestService;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.contracts.manager.ContentCompleteRequest;

@Service
public class CompleteRequestService {

    @Autowired
    private GetRequestService getRequestService;

    @Autowired
    private IRequestRepository _requestRepository;

    public void handle(ContentCompleteRequest request) {

        // Verificar viaturas
        // Verificar contentores

        // Alterar o estado do request
        Request handleRequest = getRequestService.handle(request.getRequestId());
        _requestRepository.changeState(handleRequest, State.SCHEDULED);

    }

}

// /Pronto a arrancar
// Suspenso -> Agendado -> Execução -> Concluido -> Pago
// |
// Anulado <- |
