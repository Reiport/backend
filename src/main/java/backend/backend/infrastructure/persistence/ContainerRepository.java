package backend.backend.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IContainerRepository;
import backend.backend.domain.entities.Container;
import jakarta.persistence.TypedQuery;

@Repository
public class ContainerRepository extends BaseRepository implements IContainerRepository {

    @Override
    public Container getContainerById(String license) {

        try {

            TypedQuery<Container> query = _entityManager.createQuery(
                    "SELECT c FROM Container c WHERE c.license = :license",
                    Container.class);

            return query.setParameter("license", license).getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Container> getAllContainers() {
        try {

            TypedQuery<Container> query = _entityManager.createQuery("SELECT c FROM Container c",
                    Container.class);

            var ola = query.getResultList();
            System.out.println("");

            return ola;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
