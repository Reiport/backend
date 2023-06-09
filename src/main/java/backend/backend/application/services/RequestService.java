package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Container;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Invoice;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.domain.entities.Vehicle;

@Service
public class RequestService {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private IUserRepository userRepository;

    public boolean checkMemberShip(Request request, Guest guest) {
        return !this.getRequestMembers(request.getId()).isEmpty();
    }

    public Collection<RequestInfo> getAllMyRequests() {
        return requestRepository.getMyRequests(authorizationFacade.getAuthenticatedUser());
    }

    public Collection<Driver> getAllDrivers(int requestId) {
        return requestRepository.getAllDrivers(requestRepository.getRequestById(requestId));
    }

    public Vehicle getVehicle(int requestId) {
        return requestRepository.getVehicle(requestRepository.getRequestById(requestId));
    }

    public Container getContainer(Integer id) {
        return requestRepository.getContainer(requestRepository.getRequestById(id));
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

    public RequestInfo getRequestInfo(int requestId) {
        return requestRepository.getRequestInfoById(requestId);
    }

    public void deleteRequest(int requestId) {
        requestRepository.deleteRequestById(requestId);
    }

    public Request updateRequest(Request oldRequest) {
        return requestRepository.updateRequest(oldRequest);
    }

    public Object getState(Request workingRequest) {
        return requestRepository.getRequestState(workingRequest);
    }

    public Collection<RequestInfo> getRequestToEvaluate() {
        return this.requestRepository.getRequestToEvaluate(authorizationFacade.getAuthenticatedUser());
    }

    public Collection<Invoice> getAllInvoices(int id) {
        return this.requestRepository.getAllInvoices(id);
    }

}
