package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Model;
import backend.backend.presentation.contracts.model.ModelResponse;

@Mapper
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modelContainers", ignore = true)
    @Mapping(target = "modelVehicles", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Model ToModel(ModelResponse modelResponse);

}
