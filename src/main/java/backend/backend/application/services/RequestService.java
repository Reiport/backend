package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;

@Service
public class RequestService {

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    public Collection<Driver> getAllDrivers(int requestId) {
        return requestRepository.getAllDrivers(requestRepository.getRequestById(requestId));
    }

    public Collection<RequestInfo> getAllRequestOfClient(int id) {
        return requestRepository.getAllRequests(userRepository.findClientById(id));
    }

    public Collection<RequestInfo> getAllRequests() {
        return requestRepository.getAllRequests(authorizationFacade.getAuthenticatedUser());
    }

    public Collection<Guest> getRequestMembers(int requestId) {
        return requestRepository.getGroupGuests(requestRepository.getRequestById(requestId));
    }

    public Request getRequest(int requestId) {
        return requestRepository.getRequestById(requestId);
    }

    public void deleteRequest(int requestId) {
        requestRepository.deleteRequestById(requestId);
    }

    public Object getState(Request workingRequest) {
        return requestRepository.getRequestState(workingRequest);
    }

}
