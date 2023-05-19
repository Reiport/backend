package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IModelRepository;
import backend.backend.application.common.interfaces.repositories.ITruckRepository;
import backend.backend.domain.entities.Vehicle;
import backend.backend.presentation.contracts.vehicle.TruckRequest;
import backend.backend.presentation.contracts.vehicle.UpdateTruckRequest;
import backend.backend.presentation.mappers.TruckMapper;
import jakarta.transaction.Transactional;

@Service
public class TruckService {

    @Autowired
    private IModelRepository modelRepository;

    @Autowired
    private ITruckRepository truckRepository;

    // TODO: Validate Data
    @Transactional
    public void createTruck(TruckRequest request) {
        truckRepository.save(
                TruckMapper.INSTANCE.toTruck(
                        request,
                        modelRepository.findModelByName(request.getModel())));
    }

    public void updateTruck(UpdateTruckRequest request) {
        truckRepository.updateTruck(request.getLicense(), TruckMapper.INSTANCE.toTruck(request));
    }

    public void deleteTruck(String license) {
        truckRepository.deleteByLicense(license);
    }

    public Collection<Vehicle> getAllTrucks() {
        return truckRepository.getTrucks();
    }

    public Vehicle getTruckById(String license) {
        return truckRepository.getTruckById(license);
    }

}
