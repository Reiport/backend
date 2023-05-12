package backend.backend.application.common.interfaces.repositories;

import java.util.Optional;

import backend.backend.domain.entities.Vehicle;

public interface ITruckRepository {

    Optional<Vehicle> getTruckById(String license);

}
