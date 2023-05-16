package backend.backend.infrastructure.persistence;

import java.util.Collection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseRepository {

    @PersistenceContext
    protected EntityManager _entityManager;

    public <T> T getOf(String query, Class<T> clazz) {
        return _entityManager.createQuery(query, clazz).getSingleResult();
    }

    public <T> T getOf(String query, Class<T> clazz, String errorMessage) {
        try {
            return _entityManager.createQuery(query, clazz).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(errorMessage);
        }
    }

    public <T> Collection<T> getCollectionOf(String query, Class<T> clazz, String errorMessage) {
        try {
            return _entityManager.createQuery(query, clazz).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(errorMessage);
        }
    }

}
