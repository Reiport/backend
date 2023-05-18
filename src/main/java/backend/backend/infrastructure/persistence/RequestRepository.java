package backend.backend.infrastructure.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.DriverGroup;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.GuestGroup;
import backend.backend.domain.entities.HistoricStates;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.domain.entities.State;
import backend.backend.presentation.errors.DBException;
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

            TypedQuery<Request> query = _entityManager.createQuery(
                    "SELECT r FROM Request r WHERE r.id = :id AND r.deletedAt is null",
                    Request.class);

            return query.setParameter("id", id).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum pedido");
        }

    }

    @Override
    public Collection<RequestInfo> getAllRequests(Guest guest) {

        try {

            TypedQuery<RequestInfo> query = _entityManager.createQuery(
                    "SELECT ri FROM RequestInfo ri INNER JOIN GuestGroup gg INNER JOIN Request r WHERE gg.guest = :guest AND gg.request.id = ri.id AND r.id = ri.id AND r.deletedAt is null",
                    RequestInfo.class);

            return query.setParameter("guest", guest).getResultList();

        } catch (Exception e) {
            throw new DBException("Não existe nehnum pedido ainda registado");
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
            throw new DBException("Não foi encontrado o estado do pedido");
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
    public Collection<Guest> getGroupGuests(Request request) {
        try {

            TypedQuery<Guest> query = _entityManager.createQuery(
                    "SELECT gg.guest FROM GuestGroup gg WHERE gg.request = :request",
                    Guest.class);

            return query.setParameter("request", request).getResultList();

        } catch (Exception e) {
            throw new DBException("Não existe nenhum membro no pedido");
        }
    }

    @Override
    public Guest getClient(Request request) {
        try {

            TypedQuery<Guest> query = _entityManager.createQuery(
                    "SELECT gg.guest FROM GuestGroup gg WHERE gg.request = :request AND gg.guest.guestType = 1",
                    Guest.class);

            return query.setParameter("request", request).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum cliente pertencente ao pedido");
        }
    }

    @Override
    public Collection<Driver> getAllDrivers(Request request) {
        try {

            TypedQuery<Driver> query = _entityManager.createQuery(
                    "SELECT dg.driver FROM DriverGroup dg WHERE dg.request = :request",
                    Driver.class);

            return query.setParameter("request", request).getResultList();

        } catch (Exception e) {
            throw new DBException("Não foram encontrados nenhums motoristas associados ao pedido");
        }
    }

    @Override
    public Collection<RequestInfo> getRequestToEvaluate(Guest user) {
        try {

            TypedQuery<RequestInfo> query = _entityManager.createQuery(
                    "SELECT ri FROM RequestInfo ri INNER JOIN Request r WHERE ri.state = 1 AND r.id = ri.id AND r.deletedAt is null AND ri.guest = :guest",
                    RequestInfo.class);

            return query.setParameter("guest", user).getResultList();

        } catch (Exception e) {
            throw new DBException("Não há nenhum pedido para avaliar");
        }
    }

    @Override
    public void deleteRequestById(int requestId) {
        Request request = getRequestById(requestId);

        request.setDeletedAt(LocalDate.now());
        _entityManager.merge(request);
    }

    @Override
    public Request updateRequest(Request request) {

        Request oldRequest = getRequestById(request.getId());

        return oldRequest;

    }
}
