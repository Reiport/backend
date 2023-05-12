package backend.backend.application.services.worker.manager;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.ITruckRepository;
import backend.backend.domain.entities.Vehicle;

@Service
public class GetTruckByIdService {

    @Autowired
    private ITruckRepository truckRepository;

    public Vehicle handle(String lincense) {
        Optional<Vehicle> truck = truckRepository.getTruckById(lincense);

        if (truck.isEmpty())
            throw new RuntimeException("It was not found any truck with that lincense!");

        return truck.get();
    }

}
