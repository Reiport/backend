package backend.backend.infrastructure.persistence;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.ITruckRepository;
import backend.backend.domain.entities.Vehicle;
import backend.backend.presentation.errors.DBException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TruckRepository extends BaseRepository implements ITruckRepository {

    @Override
    public Vehicle getTruckById(String license) {

        try {

            TypedQuery<Vehicle> query = _entityManager.createQuery(
                    "SELECT v FROM Vehicle v WHERE v.license = :license AND v.deletedAt is null",
                    Vehicle.class);

            return query.setParameter("license", license).getSingleResult();

        } catch (Exception e) {
            throw new DBException("Não foi encontrado nehum veiculo");
        }

    }

    @Override
    public Collection<Vehicle> getTrucks() {

        try {

            TypedQuery<Vehicle> query = _entityManager
                    .createQuery("SELECT v FROM Vehicle v WHERE v.deletedAt is null", Vehicle.class);

            return query.getResultList();

        } catch (Exception e) {
            throw new DBException("Não existe nehum veiculo registado");
        }

    }

    @Override
    public Vehicle save(Vehicle vehicle) {

        try {
            _entityManager.persist(vehicle);
        } catch (Exception e) {
            throw new DBException("Tente registar o veiculo com uma licença diferente");
        }

        return vehicle;
    }

    @Transactional
    @Override
    public void deleteByLicense(String license) {
        Vehicle truck = getTruckById(license);

        truck.setDeletedAt(LocalDate.now());
        _entityManager.merge(truck);
    }

    @Transactional
    @Override
    public void updateTruck(String license, Vehicle vehicle) {
        Vehicle truck = getTruckById(license);

        _entityManager.merge(truck);
    }

}
