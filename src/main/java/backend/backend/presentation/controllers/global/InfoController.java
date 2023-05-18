package backend.backend.presentation.controllers.global;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.backend.application.services.InfoService;
import backend.backend.domain.entities.Brand;
import backend.backend.domain.entities.Country;
import backend.backend.domain.entities.Model;
import backend.backend.presentation.contracts.info.BrandResponse;
import backend.backend.presentation.contracts.info.CountryResponse;
import backend.backend.presentation.contracts.info.ModelResponse;
import backend.backend.presentation.mappers.ModelMapper;

@Controller
public class InfoController {

    @Autowired
    private InfoService infoService;

    @PreAuthorize("hasAuthority('Rececionista') and hasAuthority('Gestor')")
    @GetMapping("/brands")
    public ResponseEntity<Collection<BrandResponse>> getAllBrands() {

        Collection<Brand> result = infoService.getAllBrands();

        var response = result
                .stream()
                .map(brand -> ModelMapper.INSTANCE.toBrandResponse(brand))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('Rececionista') and hasAuthority('Gestor')")
    @GetMapping("/models")
    public ResponseEntity<Collection<ModelResponse>> getAllModels(@RequestParam int brandId) {

        Collection<Model> result = infoService.getAllModels(brandId);

        var response = result
                .stream()
                .map(model -> ModelMapper.INSTANCE.toModelResponse(model))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('Rececionista') and hasAuthority('Gestor')")
    @GetMapping("/countries")
    public ResponseEntity<Collection<CountryResponse>> getAllCountries() {

        Collection<Country> result = infoService.getAllCountries();

        var response = result
                .stream()
                .map(country -> ModelMapper.INSTANCE.toCountryResponse(country))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
