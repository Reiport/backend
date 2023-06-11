package backend.backend.infrastructure.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Container;
import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.DriverGroup;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.GuestGroup;
import backend.backend.domain.entities.HistoricStates;
import backend.backend.domain.entities.Invoice;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.domain.entities.State;
import backend.backend.domain.entities.Statistics;
import backend.backend.domain.entities.Vehicle;
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
        driver.setIsWorking(true);
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
                    "SELECT ri FROM RequestInfo ri INNER JOIN GuestGroup gg INNER JOIN Request r WHERE gg.guest = :guest AND gg.request.id = ri.id AND r.id = ri.id AND ri.state = 1 AND r.deletedAt is null",
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
        return _entityManager.merge(request);
    }

    @Override
    public RequestInfo getRequestInfoById(int requestId) {
        try {

            TypedQuery<RequestInfo> query = _entityManager.createQuery(
                    "SELECT ri FROM RequestInfo ri INNER JOIN Request r WHERE ri.id = :id AND ri.id = r.id AND r.deletedAt is null",
                    RequestInfo.class);

            return query.setParameter("id", requestId).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum pedido");
        }
    }

    @Override
    public Vehicle getVehicle(Request request) {
        try {

            TypedQuery<Vehicle> query = _entityManager.createQuery(
                    "SELECT v FROM Vehicle v INNER JOIN Request r WHERE r = :request AND r.license = v.license",
                    Vehicle.class);

            return query.setParameter("request", request).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foram encontrados nenhums motoristas associados ao pedido");
        }
    }

    @Override
    public Container getContainer(Request request) {
        try {

            TypedQuery<Container> query = _entityManager.createQuery(
                    "SELECT c FROM Container c INNER JOIN Request r WHERE r = :request AND r.containerLicense = c.license",
                    Container.class);

            return query.setParameter("request", request).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foram encontrados nenhums motoristas associados ao pedido");
        }
    }

    @Override
    public Collection<RequestInfo> getMyRequests(Guest authUser) {
        try {

            TypedQuery<RequestInfo> query = _entityManager.createQuery(
                    "SELECT ri FROM RequestInfo ri INNER JOIN Request r INNER JOIN GuestGroup gp WHERE gp.guest = :my AND gp.request = r AND ri.id = r.id AND r.deletedAt is null",
                    RequestInfo.class);

            return query.setParameter("my", authUser).getResultList();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum pedido");
        }
    }

    @Override
    public void addInvoice(Request request, Invoice invoice) {
        invoice.setRequest(request);
        _entityManager.persist(invoice);
    }

    @Override
    public Collection<Invoice> getAllInvoices(int id) {
        try {

            TypedQuery<Invoice> query = _entityManager.createQuery(
                    "SELECT i FROM Invoice i WHERE i.request.id = :request",
                    Invoice.class);

            return query.setParameter("request", id).getResultList();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhuma fatura");
        }
    }

    @Override
    public Invoice getInvoice(int id) {
        try {

            TypedQuery<Invoice> query = _entityManager.createQuery(
                    "SELECT i FROM Invoice i WHERE i.id = :invoice",
                    Invoice.class);

            return query.setParameter("invoice", id).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhuma fatura");
        }
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return _entityManager.merge(invoice);
    }

    @Override
    public Statistics getStatistics(Guest user) {
        TypedQuery<Long> query;

        try {

            query = _entityManager.createQuery(
                    "SELECT COUNT(r.id) FROM RequestInfo r WHERE r.state = 0 AND r.client = :user",
                    Long.class);

            var exeCount = query.setParameter("user", user).getSingleResult();

            query = _entityManager.createQuery(
                    "SELECT COUNT(r.id) FROM RequestInfo r WHERE r.state = 1 AND r.client = :user",
                    Long.class);

            var susCount = query.setParameter("user", user).getSingleResult();

            query = _entityManager.createQuery(
                    "SELECT COUNT(r.id) FROM RequestInfo r WHERE r.state = 2 AND r.client = :user",
                    Long.class);

            var delCount = query.setParameter("user", user).getSingleResult();

            query = _entityManager.createQuery(
                    "SELECT COUNT(r.id) FROM RequestInfo r WHERE r.state = 3 AND r.client = :user",
                    Long.class);

            var comCount = query.setParameter("user", user).getSingleResult();

            query = _entityManager.createQuery(
                    "SELECT COUNT(r.id) FROM RequestInfo r WHERE r.state = 4 AND r.client = :user",
                    Long.class);

            var schCount = query.setParameter("user", user).getSingleResult();

            query = _entityManager.createQuery(
                    "SELECT COUNT(r.id) FROM RequestInfo r WHERE r.state = 5 AND r.client = :user",
                    Long.class);

            var payCount = query.setParameter("user", user).getSingleResult();

            return new Statistics(
                    exeCount,
                    susCount,
                    delCount,
                    comCount,
                    schCount,
                    payCount);

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhuma fatura");
        }
    }

    @Override
    public Collection<RequestInfo> getRequestToDeliver(Guest authenticatedUser) {
        try {

            TypedQuery<RequestInfo> query = _entityManager.createQuery(
                    "SELECT r FROM RequestInfo r INNER JOIN DriverGroup dg WHERE dg.request = r AND dg.driver.guest_id = :driver AND r.state in (0,2,4)",
                    RequestInfo.class);

            return query.setParameter("driver", authenticatedUser).getResultList();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum pedido");
        }
    }

    @Override
    public Collection<RequestInfo> getSchedualDelivers(Guest authenticatedUser) {

        try {

            TypedQuery<RequestInfo> query = _entityManager.createQuery(
                    "SELECT r FROM RequestInfo r INNER JOIN DriverGroup dg WHERE dg.request = r AND dg.driver.guest_id = :driver AND r.state = 4",
                    RequestInfo.class);

            return query.setParameter("driver", authenticatedUser).getResultList();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum pedido");
        }

    }

    @Override
    public RequestInfo getCurrentExecuteDeliver(Guest authenticatedUser) {

        try {

            TypedQuery<RequestInfo> query = _entityManager.createQuery(
                    "SELECT r FROM RequestInfo r INNER JOIN DriverGroup dg WHERE dg.driver.isWorking = true AND dg.request = r AND dg.driver.guest_id = :driver AND r.state = 0 ORDER BY dg.createdAt DESC",
                    RequestInfo.class);

            return query.setParameter("driver", authenticatedUser).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum pedido");
        }

    }

}
