package backend.backend.application.services.authentication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

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

    public AuthenticationResult handle(RegisterRequest registerRequest) {

        var postalCode = this.postalCodeRepository.findByCode(registerRequest.getPostalCode());

        var user = this.userRepository.findByEmail(registerRequest.getEmail());

        if (user.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }

        var client = new Guest(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                LocalDate.parse(registerRequest.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                registerRequest.getNif(),
                registerRequest.getStreet(),
                registerRequest.getPort(),
                registerRequest.getTelephone(),
                postalCode,
                guestTypeRepository.findByName("Cliente").get());

        client.setAvatar(
                "https://rjgetrgsupehkuqfhpqq.supabase.co/storage/v1/object/sign/SUPASUPAPROJ/avatar5.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJTVVBBU1VQQVBST0ovYXZhdGFyNS5wbmciLCJpYXQiOjE2ODY2OTQ3OTIsImV4cCI6MTcxODIzMDc5Mn0.A6pY5cWFJB6m-TIq0SO6vh5OGorswqqXHwh3MQEQlFU&t=2023-06-13T22%3A19%3A51.256Z");
        var createdUser = this.userRepository.save(client);

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
                "localhost:7000/auth/validate_account?token=" + validation_token);
        options.put("password", registerRequest.getPassword());

        mailSender.sendEmail(
                "Registro na plataforma Reiport",
                registerRequest.getEmail(),
                "welcome",
                options);

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
