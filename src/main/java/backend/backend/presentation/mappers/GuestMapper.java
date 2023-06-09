package backend.backend.presentation.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Driver;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.UpdateProfile;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import backend.backend.presentation.contracts.authentication.RegisterWorkerRequest;
import backend.backend.presentation.contracts.worker.DriverResponse;
import backend.backend.presentation.contracts.worker.WorkerResponse;

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
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    Guest registerRequestToGuest(RegisterRequest request);

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
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    Guest updateProfileToGuest(UpdateProfile request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guestType", ignore = true)
    @Mapping(source = "postalCode", target = "postalCode.id")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "clientRequests", ignore = true)
    @Mapping(target = "idDrivers", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "guestHistoricStatess", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    Guest registerWorkerRequestToGuest(RegisterWorkerRequest request);

    @Mapping(target = "postalCode", source = "postalCode.id")
    @Mapping(target = "guestType", source = "guestType.name")
    WorkerResponse toWorkerResponse(Guest guest);

    @Mapping(source = "guest_id", target = "guest")
    DriverResponse toDriverResponse(Driver composeDriver);

    public static String localDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var data = date.format(formatter);
        return data;
    }

    public static LocalDate localDateToString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        String formattedDate = localDate.format(formatter);
        return LocalDate.parse(formattedDate);
    }

}
