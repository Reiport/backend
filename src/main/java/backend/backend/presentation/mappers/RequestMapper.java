package backend.backend.presentation.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Invoice;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.RequestInfo;
import backend.backend.presentation.contracts.invoice.InvoiceResponse;
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
    @Mapping(target = "countryOri", ignore = true)
    @Mapping(target = "countryDest", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "localityDest", ignore = true)
    @Mapping(target = "localityOri", ignore = true)
    @Mapping(target = "state", ignore = true)
    RequestResponse toRequestResponse(Request request);

    @Mapping(target = "postalCodeDest", source = "postalCodeDest")
    @Mapping(target = "postalCodeOri", source = "postalCodeOri")
    @Mapping(target = "containerLicense", source = "containerLicense")
    @Mapping(target = "containerLicenseSecond", source = "containerLicenseSecond")
    @Mapping(target = "license", source = "vehicleLicense")
    @Mapping(target = "client.postalCode", source = "client.postalCode.id")
    @Mapping(target = "client.guestType", source = "client.guestType.name")
    RequestResponse toRequestInfoResponse(RequestInfo request);

    @Mapping(target = "postalCode", source = "postalCode.id")
    InvoiceResponse toInvoiceResponse(Invoice invoice);

    public static String localDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (date == null) {
            return "";
        }

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
