package backend.backend.application.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.domain.entities.Country;

public interface ICountryRepository extends JpaRepository<Country, Integer> {

    Country findByCountry(String country);

}
