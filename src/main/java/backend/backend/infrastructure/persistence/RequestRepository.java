package backend.backend.infrastructure.persistence;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.GuestGroup;
import backend.backend.domain.entities.Request;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

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

}
