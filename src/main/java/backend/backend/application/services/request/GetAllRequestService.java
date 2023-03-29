package backend.backend.application.services.request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Request;

@Service
public class GetAllRequestService {

    @Autowired
    private IRequestRepository _requestRepository;

    public Collection<Request> handle() {
        return _requestRepository.getAllRequests();
    }

}
