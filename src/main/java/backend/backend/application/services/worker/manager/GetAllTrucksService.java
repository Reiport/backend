package backend.backend.application.services.worker.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.ITruckRepository;
import backend.backend.domain.entities.Vehicle;

@Service
public class GetAllTrucksService {

    @Autowired
    private ITruckRepository truckRepository;

    public Collection<Vehicle> handle() {
        return truckRepository.getTrucks();
    }

}
