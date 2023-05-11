package backend.backend.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.domain.entities.Guest;

@Service
public class GetProfileService {

    @Autowired
    private IAuthorizationFacade _authorizationFacade;

    public Guest handle() {
        return _authorizationFacade.getAuthenticatedUser();
    }

}
