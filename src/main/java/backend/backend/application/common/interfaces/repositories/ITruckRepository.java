package backend.backend.application.common.interfaces.repositories;

import java.util.Collection;

import backend.backend.domain.entities.Vehicle;

public interface ITruckRepository {

    Vehicle getTruckById(String license);

    Collection<Vehicle> getTrucks();

}
