package backend.backend.infrastructure.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.IContainerRepository;
import backend.backend.domain.entities.Container;
import backend.backend.presentation.errors.DBException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

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
            throw new DBException("Não foi encontrado nenhum contentor");
        }

    }

    @Override
    public List<Container> getAllContainers() {
        try {

            TypedQuery<Container> query = _entityManager.createQuery(
                    "SELECT c FROM Container c WHERE c.deletedAt is null",
                    Container.class);

            return query.getResultList();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nenhum contentor");
        }
    }

    @Transactional
    @Override
    public Container createContainer(Container container) {

        try {
            _entityManager.persist(container);
        } catch (EntityExistsException e) {
            throw new DBException("Tente registar o contentor com uma licença diferente");
        }

        return container;

    }

    @Override
    public void deleteByLincense(String license) {
        Container container = getContainerById(license);

        container.setDeletedAt(LocalDate.now());
        _entityManager.merge(container);
    }

}
