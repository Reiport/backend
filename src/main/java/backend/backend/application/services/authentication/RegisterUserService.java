package backend.backend.application.services.authentication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IGuestTypeRepository;
import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.application.common.interfaces.repositories.ITokenRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import backend.backend.presentation.errors.authentication.UserAlreadyRegisteredException;
import backend.backend.domain.entities.redis.Token;

@Service
public class RegisterUserService {

    @Autowired
    private IGuestTypeRepository guestTypeRepository;

    @Autowired
    private IPostalCodeRepository postalCodeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IJwtGenerator jwtGenerator;

    @Autowired
    private IMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResult handle(RegisterRequest registerRequest) {

        var postalCode = this.postalCodeRepository.findByCode(registerRequest.getPostalCode());

        var user = this.userRepository.findByEmail(registerRequest.getEmail());

        if (user.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }

        // TODO: Consertar a busca do tipo de utilizador ao banco
        var createdUser = this.userRepository.save(
                new Guest(
                        registerRequest.getEmail(),
                        passwordEncoder.encode(registerRequest.getPassword()),
                        registerRequest.getFirstName(),
                        registerRequest.getLastName(),
                        LocalDate.parse(registerRequest.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        registerRequest.getNif(),
                        registerRequest.getStreet(),
                        registerRequest.getPort(),
                        registerRequest.getTelephone(),
                        postalCode,
                        guestTypeRepository.findByName("Cliente").get()));

        Map<String, Object> options = new HashMap<>();

        String validation_token = jwtGenerator.generateSimpleToken();

        tokenRepository.save(
                new Token(
                        createdUser.getId(),
                        validation_token,
                        new Date(System.currentTimeMillis() + 15 * 60 * 1000).getTime() // 15 min
                ));

        options.put(
                "verifyLink",
                "localhost:8080/auth/validate_account?token=" + validation_token);

        mailSender.sendEmail(
                "Registro na plantaforma Reiport",
                registerRequest.getEmail(),
                "welcome",
                options);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(createdUser, registerRequest.getPassword()));

        Collection<String> authorities = createdUser.getAuthorities().stream().map(role -> role.getAuthority())
                .collect(Collectors.toList());

        var token = jwtGenerator.generateToken(
                createdUser.getId().toString(),
                registerRequest.getEmail(),
                authorities, 30 * 60 * 1000);

        var refresh_token = jwtGenerator.generateToken(
                createdUser.getId().toString(),
                registerRequest.getEmail(),
                authorities, 2 * 60 * 60 * 1000);

        return new AuthenticationResult(
                token,
                refresh_token);

    }

}
