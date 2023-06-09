package backend.backend.application.common.interfaces.repositories;

import java.math.BigDecimal;
import java.util.Collection;

import backend.backend.domain.entities.Container;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Invoice;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.domain.entities.State;
import backend.backend.domain.entities.Vehicle;

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

    Guest getClient(Request request);

    Collection<Driver> getAllDrivers(Request request);

    State getRequestState(Request request);

    void changeState(Request request, State state, Guest guest);

    /**
     * This method will add drivers to the request
     *
     * @param driver
     */
    void addDriver(Request request, Driver driver, BigDecimal kilometers);

    void deleteRequestById(int requestId);

    Request updateRequest(Request oldRequest);

    RequestInfo getRequestInfoById(int requestId);

    Vehicle getVehicle(Request request);

    Container getContainer(Request request);

    Collection<RequestInfo> getMyRequests(Guest authUser);

    void addInvoice(Request request, Invoice invoice);

    Collection<Invoice> getAllInvoices(int id);

    Invoice getInvoice(int id);

    Invoice updateInvoice(Invoice invoice);

}
