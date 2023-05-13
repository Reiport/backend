package backend.backend.infrastructure.persistence;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.DriverGroup;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.GuestGroup;
import backend.backend.domain.entities.HistoricStates;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class RequestRepository implements IRequestRepository {

    @PersistenceContext
    private EntityManager _entityManager;

    @Override
    public void linkGuest(Guest guest, Request request) {
        _entityManager.persist(new GuestGroup(request, guest));
    }

    @Override
    public Request save(Request request) {
        _entityManager.persist(request);
        return request;
    }

    @Override
    public Request getRequestById(int id) {

        try {

            TypedQuery<Request> query = _entityManager.createQuery("SELECT r FROM Request r WHERE r.id = :id",
                    Request.class);

            return query.setParameter("id", id).getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Collection<Request> getAllRequests() {

        try {

            TypedQuery<Request> query = _entityManager.createQuery("SELECT r FROM Request r",
                    Request.class);

            return query.getResultList();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public State getRequestState(Request request) {
        try {

            TypedQuery<State> query = _entityManager.createQuery(
                    "SELECT hs.state FROM HistoricStates hs WHERE hs.request = :request ORDER BY hs.id DESC LIMIT 1",
                    State.class);

            return query.setParameter("request", request).getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    @Transactional
    public void changeState(Request request, State state, Guest user) {
        _entityManager.persist(new HistoricStates(state, request, user));
    }

    @Override
    @Transactional
    public void addDriver(Request request, Driver driver, BigDecimal kilometers) {
        _entityManager.persist(new DriverGroup(kilometers, request, driver));
    }

    @Override
    public Optional<Collection<Guest>> getGroupGuests(Request request) {
        try {

            TypedQuery<Guest> query = _entityManager.createQuery(
                    "SELECT gg.guest FROM GuestGroup gg WHERE gg.request = :request",
                    Guest.class);

            return Optional.of(query.setParameter("request", request).getResultList());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Guest> getClient(Request request) {
        try {

            TypedQuery<Guest> query = _entityManager.createQuery(
                    "SELECT gg.guest FROM GuestGroup gg WHERE gg.request = :request AND gg.guest.guestType = 1",
                    Guest.class);

            return Optional.of(query.setParameter("request", request).getSingleResult());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
