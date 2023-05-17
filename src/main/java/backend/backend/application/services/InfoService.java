package backend.backend.application.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IBrandRepository;
import backend.backend.application.common.interfaces.repositories.ICountryRepository;
import backend.backend.application.common.interfaces.repositories.IModelRepository;
import backend.backend.domain.entities.Brand;
import backend.backend.domain.entities.Country;
import backend.backend.domain.entities.Model;

@Service
public class InfoService {

    @Autowired
    private IModelRepository modelRepository;

    @Autowired
    private IBrandRepository brandRepository;

    @Autowired
    private ICountryRepository countryRepository;

    public Collection<Model> getAllModels(int brandId) {
        return modelRepository.findModelByBrandId(brandId);
    }

    public Collection<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Collection<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

}
