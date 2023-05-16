package backend.backend.application.common.interfaces.repositories;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.domain.entities.State;

public interface IRequestRepository {

    Collection<RequestInfo> getAllRequests(Guest user);

    Collection<RequestInfo> getRequestToEvaluate(Guest user);

    Request getRequestById(int id);

    Request save(Request request);

    /**
     * Link the Guest User to the Request
     *
     * @param guest
     * @param request
     */
    void linkGuest(Guest guest, Request request);

    /**
     * Get all working guests in request
     *
     * @param request
     * @return
     */
    Collection<Guest> getGroupGuests(Request request);

    Optional<Guest> getClient(Request request);

    Collection<Driver> getAllDrivers(Request request);

    State getRequestState(Request request);

    void changeState(Request request, State state, Guest guest);

    /**
     * This method will add drivers to the request
     *
     * @param driver
     */
    void addDriver(Request request, Driver driver, BigDecimal kilometers);

}
