package backend.backend.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseRepository {

    @PersistenceContext
    protected EntityManager _entityManager;

}
