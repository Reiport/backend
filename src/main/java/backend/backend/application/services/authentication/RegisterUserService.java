package backend.backend.application.services.authentication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IGuestTypeRepository;
import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import backend.backend.presentation.errors.authentication.UserAlreadyRegisteredException;

@Service
public class RegisterUserService {

    private final IGuestTypeRepository guestTypeRepository;
    private final IPostalCodeRepository postalCodeRepository;
    private final IUserRepository userRepository;
    private final IJwtGenerator jwtGenerator;
    private final IMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterUserService(
            IGuestTypeRepository guestTypeRepository,
            IPostalCodeRepository postalCodeRepository,
            IUserRepository userRepository,
            IJwtGenerator jwtGenerator,
            IMailSender mailSender,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.guestTypeRepository = guestTypeRepository;
        this.postalCodeRepository = postalCodeRepository;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResult handle(RegisterRequest registerRequest) {

        var guest_type = this.guestTypeRepository.findByName(registerRequest.getGuestType());

        var postalCode = this.postalCodeRepository.findById(registerRequest.getPostalCode());

        var user = this.userRepository.findByEmail(registerRequest.getEmail());

        if (user.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }

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
                        postalCode.get(),
                        guest_type.get()));

        Map<String, Object> options = new HashMap<>();

        options.put("verifyLink", "");

        // Send Email
        mailSender.sendEmail(
                "Registro na plantaforma Reiport",
                registerRequest.getEmail(),
                "welcome",
                options);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(createdUser, registerRequest.getPassword()));

        var token = jwtGenerator.generateToken(
                createdUser.getId().toString(),
                registerRequest.getEmail());

        var refresh_token = jwtGenerator.generateToken(
                createdUser.getId().toString(),
                registerRequest.getEmail());

        return new AuthenticationResult(
                token,
                refresh_token);

    }

}
