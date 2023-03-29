package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Request;
import backend.backend.presentation.contracts.request.RequestResponse;

@Mapper
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    // TODO: Display id Client e não display id request
    @Mapping(target = "postalCodeDest", source = "postalCodeDest.id")
    @Mapping(target = "postalCodeOri", source = "postalCodeOri.id")
    @Mapping(target = "containerLicense", source = "containerLicense.license")
    @Mapping(target = "containerLicenseSecond", source = "containerLicenseSecond.license")
    @Mapping(target = "license", source = "license.license")
    @Mapping(target = "invoice_id", source = "invoice.id")
    @Mapping(target = "client.id", ignore = true)
    @Mapping(target = "client.postalCode", source = "client.postalCode.id")
    @Mapping(target = "client.guestType", source = "client.guestType.name")
    RequestResponse toRequestResponse(Request request);

}
