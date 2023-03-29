package backend.backend.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.domain.entities.PostalCode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class PostalCodeRepository implements IPostalCodeRepository {

    @PersistenceContext
    private EntityManager _entityManager;

    @Override
    public PostalCode findByCode(String postalCode) {

        try {

            TypedQuery<PostalCode> query = _entityManager.createQuery(
                    "select p from PostalCode p where p.id = :code", PostalCode.class);

            return query.setParameter("code", postalCode).getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException("There is no Postal Code");
        }

    }

    @Override
    public PostalCode save(PostalCode postalCode) {
        _entityManager.persist(postalCode);
        return postalCode;
    }

}
