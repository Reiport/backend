package backend.backend.application.common.interfaces.repositories;

import java.math.BigDecimal;
import java.util.Collection;

import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;

public interface IRequestRepository {

    Collection<Request> getAllRequests();

    Request getRequestById(int id);

    Request save(Request request);

    void linkGuest(Guest guest, Request request);

    State getRequestState(Request request);

    void changeState(Request request, State state);

    /**
     * This method will add drivers to the request
     *
     * @param driver
     */
    void addDriver(Request request, Driver driver, BigDecimal kilometers);

}
