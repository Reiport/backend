package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Model;
import backend.backend.domain.entities.Vehicle;
import backend.backend.presentation.contracts.vehicle.TruckRequest;
import backend.backend.presentation.contracts.vehicle.TruckResponse;
import backend.backend.presentation.contracts.vehicle.UpdateTruckRequest;

@Mapper
public interface TruckMapper {

    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);

    TruckResponse ToTruckResponse(Vehicle vehicle);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isInUse", ignore = true)
    @Mapping(target = "licenseRequests", ignore = true)
    @Mapping(target = "model", source = "model")
    Vehicle toTruck(TruckRequest request, Model model);

    @Mapping(target = "license", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isInUse", ignore = true)
    @Mapping(target = "licenseRequests", ignore = true)
    @Mapping(target = "fuel", ignore = true)
    @Mapping(target = "model", ignore = true)
    Vehicle toTruck(UpdateTruckRequest request);

}
