package backend.backend.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.presentation.contracts.request.RequestResponse;

@Mapper
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(target = "postalCodeDest", source = "postalCodeDest.id")
    @Mapping(target = "postalCodeOri", source = "postalCodeOri.id")
    @Mapping(target = "containerLicense", source = "containerLicense.license")
    @Mapping(target = "containerLicenseSecond", source = "containerLicenseSecond.license")
    @Mapping(target = "license", source = "license.license")
    @Mapping(target = "client.postalCode", source = "client.postalCode.id")
    @Mapping(target = "client.guestType", source = "client.guestType.name")
    RequestResponse toRequestResponse(Request request);

    @Mapping(target = "postalCodeDest", source = "postalCodeDest")
    @Mapping(target = "postalCodeOri", source = "postalCodeOri")
    @Mapping(target = "containerLicense", source = "containerLicense")
    @Mapping(target = "containerLicenseSecond", source = "containerLicenseSecond")
    @Mapping(target = "license", source = "vehicleLicense")
    @Mapping(target = "client.postalCode", source = "client.postalCode.id")
    @Mapping(target = "client.guestType", source = "client.guestType.name")
    RequestResponse toRequestInfoResponse(RequestInfo request);

}
