package backend.backend.application.common.interfaces.repositories;

import java.util.Collection;

import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;

public interface IRequestRepository {

    Collection<Request> getAllRequests();

    Request getRequestById(int id);

    void linkGuest(Guest guest, Request request);

    Request save(Request request);

}
