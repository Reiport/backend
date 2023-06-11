package backend.backend.infrastructure.providers.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;

@Component
public class AuthenticationProvider implements UserDetailsService {

    private final IUserRepository userRepository;

    public AuthenticationProvider(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Guest userDetails = null;

        var userFound = this.userRepository.findByEmail(email);

        if (userFound.isEmpty()) {
            throw new UsernameNotFoundException("There is no User with that email!");
        }

        userDetails = userFound.get();

        return userDetails;

    }

}
