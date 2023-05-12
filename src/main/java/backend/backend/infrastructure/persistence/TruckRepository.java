package backend.backend.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.ITruckRepository;
import backend.backend.domain.entities.Vehicle;
import jakarta.persistence.TypedQuery;

@Repository
public class TruckRepository extends BaseRepository implements ITruckRepository {

    @Override
    public Optional<Vehicle> getTruckById(String license) {

        try {

            TypedQuery<Vehicle> query = _entityManager.createQuery(
                    "SELECT v FROM Vehicle v WHERE v.license = :license",
                    Vehicle.class);

            return Optional.of(query.setParameter("license", license).getSingleResult());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
