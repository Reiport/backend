package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Container;
import backend.backend.presentation.contracts.container.ContainerResponse;

@Mapper
public interface ContainerMapper {

    ContainerMapper INSTANCE = Mappers.getMapper(ContainerMapper.class);

    ContainerResponse toContainerResponse(Container container);

}
