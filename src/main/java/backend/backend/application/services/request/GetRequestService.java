package backend.backend.application.services.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Request;

@Service
public class GetRequestService {

    @Autowired
    private IRequestRepository _requestRepository;

    public Request handle(int requestId) {
        return _requestRepository.getRequestById(requestId);
    }

}
