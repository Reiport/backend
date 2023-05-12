package backend.backend.application.services.request;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.presentation.errors.request.WasNotFoundAnyGroupRequest;

@Service
public class GetRequestMembersService {

    @Autowired
    private IRequestRepository _requestRepository;

    @Autowired
    private GetRequestService getRequestService;

    public Collection<Guest> handle(int requestId) {

        Request workingRequest = getRequestService.handle(requestId);

        Optional<Collection<Guest>> workingGroup = _requestRepository.getGroupGuests(workingRequest);

        if (workingGroup.isEmpty()) {
            throw new WasNotFoundAnyGroupRequest();
        }

        return workingGroup.get();

    }

}
