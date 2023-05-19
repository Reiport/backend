package backend.backend.application.services.authentication;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.domain.entities.Guest;
import backend.backend.infrastructure.providers.authentication.JwtGenerator;
import backend.backend.presentation.errors.authentication.TokenIsNoLongerValid;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@Service
public class RenewAuthenticationTokensService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtGenerator tokenGenerator;

    public AuthenticationResult handle(String refreshToken) {

        Guest authUser;

        if (!tokenGenerator.validateToken(refreshToken))
            throw new TokenIsNoLongerValid();

        String userEmail = tokenGenerator.decodeToken(refreshToken)
                .getClaim("email")
                .asString();

        Optional<Guest> workingUser = userRepository.findByEmail(userEmail);

        if (workingUser.isEmpty())
            throw new UserNotFoundException();

        authUser = workingUser.get();

        Collection<String> authorities = authUser.getAuthorities().stream().map(role -> role.getAuthority())
                .collect(Collectors.toList());

        return new AuthenticationResult(
                tokenGenerator.generateToken(authUser.getId().toString(), authUser.getEmail(), authorities),
                tokenGenerator.generateToken(authUser.getId().toString(), authUser.getEmail(), authorities));

    }

}
