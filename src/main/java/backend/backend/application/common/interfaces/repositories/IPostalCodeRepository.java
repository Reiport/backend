package backend.backend.application.common.interfaces.repositories;

import backend.backend.domain.entities.PostalCode;

public interface IPostalCodeRepository {

    PostalCode findByCode(String postalCode);

    PostalCode save(PostalCode postalCode);

}
