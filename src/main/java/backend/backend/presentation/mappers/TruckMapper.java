package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Vehicle;
import backend.backend.presentation.contracts.vehicle.TruckResponse;

@Mapper
public interface TruckMapper {

    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);

    TruckResponse ToTruckResponse(Vehicle vehicle);

}
