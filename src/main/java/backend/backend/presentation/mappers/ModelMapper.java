package backend.backend.presentation.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import backend.backend.domain.entities.Brand;
import backend.backend.domain.entities.Country;
import backend.backend.domain.entities.Model;
import backend.backend.presentation.contracts.info.BrandResponse;
import backend.backend.presentation.contracts.info.CountryResponse;
import backend.backend.presentation.contracts.info.ModelResponse;

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
    @Mapping(source = "launchDate", target = "launchDate", dateFormat = "dd/MM/yyyy")
    Model ToModel(ModelResponse modelResponse);

    @Mapping(source = "launchDate", target = "launchDate", dateFormat = "dd/MM/yyyy")
    ModelResponse toModelResponse(Model model);

    CountryResponse toCountryResponse(Country country);

    BrandResponse toBrandResponse(Brand country);

    public static LocalDate localDateToString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        String formattedDate = localDate.format(formatter);
        return LocalDate.parse(formattedDate);
    }

}
