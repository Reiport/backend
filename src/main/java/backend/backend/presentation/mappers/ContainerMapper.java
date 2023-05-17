package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Container;
import backend.backend.domain.entities.Model;
import backend.backend.presentation.contracts.container.ContainerRequest;
import backend.backend.presentation.contracts.container.ContainerResponse;

@Mapper
public interface ContainerMapper {

    ContainerMapper INSTANCE = Mappers.getMapper(ContainerMapper.class);

    ContainerResponse toContainerResponse(Container container);

    @Mapping(target = "isInUse", ignore = true)
    @Mapping(target = "containerLicenseRequests", ignore = true)
    @Mapping(target = "containerLicenseSecondRequests", ignore = true)
    @Mapping(target = "model", source = "model")
    Container toContainer(ContainerRequest request, Model model);

}
