package backend.backend.application.services.authentication;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.presentation.contracts.authentication.LoginRequest;
import backend.backend.presentation.errors.authentication.PasswordDontMatchException;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@Service
public class LoginService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public LoginService(
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            IJwtGenerator jwtGenerator,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResult handle(LoginRequest request) {

        // verify if user is registered
        var userFound = this.userRepository.findByEmail(request.getEmail());

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        // verify passwords
        if (!passwordEncoder.matches(request.getPassword(), userFound.get().getPassword())) {
            throw new PasswordDontMatchException();
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userFound.get(), request.getPassword()));

        Collection<String> authorities = userFound.get().getAuthorities().stream().map(role -> role.getAuthority())
                .collect(Collectors.toList());

        String token = jwtGenerator.generateToken(userFound.get().getId().toString(), request.getEmail(),
                authorities, 30 * 60 * 1000);

        String refreshToken = jwtGenerator.generateToken(userFound.get().getId().toString(),
                request.getEmail(), authorities, 2 * 60 * 60 * 1000);

        return new AuthenticationResult(
                token,
                refreshToken);

    }

}
