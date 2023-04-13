package backend.backend.application.common.interfaces;

import backend.backend.domain.entities.Guest;

public interface IAuthorizationFacade {

    Guest getAuthenticatedUser();

}
