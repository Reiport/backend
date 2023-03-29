package backend.backend.infrastructure.providers.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;

@Component
public class AutorizationFacade implements IAuthorizationFacade {

    @Autowired
    private IUserRepository _userRepository;

    @Override
    public Guest getAuthenticatedUser() {
        String authEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this._userRepository.findByEmail(authEmail).get();
    }

}
