package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.ITruckRepository;
import backend.backend.domain.entities.Vehicle;

@Service
public class TruckService {

    @Autowired
    private ITruckRepository truckRepository;

    public Collection<Vehicle> getAllTrucks() {
        return truckRepository.getTrucks();
    }

    public Vehicle getTruckById(String license) {
        return truckRepository.getTruckById(license);
    }

}
