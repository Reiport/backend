package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import backend.backend.presentation.contracts.authentication.RegisterWorkerRequest;

@Mapper
public interface GuestMapper {

    GuestMapper INSTANCE = Mappers.getMapper(GuestMapper.class);

    @Mapping(source = "guestType", target = "guestType.name")
    @Mapping(source = "postalCode", target = "postalCode.id")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "clientRequests", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idDrivers", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "guestHistoricStatess", ignore = true)
    Guest registerRequestToGuest(RegisterRequest request);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "guestType", ignore = true)
    @Mapping(source = "postalCode", target = "postalCode.id")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "clientRequests", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idDrivers", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "guestHistoricStatess", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    Guest registerWorkerRequestToGuest(RegisterWorkerRequest request);

}
