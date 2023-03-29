package backend.backend.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.GuestGroup;
import backend.backend.domain.entities.Request;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

}
