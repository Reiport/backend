package backend.backend.infrastructure.persistence;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import backend.backend.application.common.interfaces.repositories.ITruckRepository;
import backend.backend.domain.entities.Vehicle;
import jakarta.persistence.EntityExistsException;
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
            throw new RuntimeException("Não foi encontrado nehum veiculo");
        }

    }

    // TODO: Fix this
    @Override
    public Collection<Vehicle> getTrucks() {

        Collection<Vehicle> trucks;

        try {
            trucks = _entityManager.createQuery("SELECT v FROM Vehicle v where v.deletedAt is null", Vehicle.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Não existe nehum veiculo registado");
        }

        return trucks;

    }

    @Override
    public Vehicle save(Vehicle vehicle) {

        try {
            _entityManager.persist(vehicle);
        } catch (EntityExistsException e) {
            throw new RuntimeException("Tente registar o veiculo com uma licença diferente");
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

    // TODO: ALL UPDATES
    @Transactional
    @Override
    public void updateTruck(String license, Vehicle vehicle) {
        Vehicle truck = getTruckById(license);

        _entityManager.merge(truck);
    }

}
