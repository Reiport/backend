package backend.backend.application.services.authentication;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.ITokenRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.redis.Token;
import backend.backend.presentation.contracts.authentication.ForgotPasswordRequest;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@Service
public class ForgotPasswordService {

    private final ITokenRepository tokenRepository;
    private final IUserRepository userRepository;
    private final IJwtGenerator jwtGenerator;
    private final IMailSender mailSender;

    public ForgotPasswordService(
            ITokenRepository tokenRepository,
            IUserRepository userRepository,
            IJwtGenerator jwtGenerator,
            IMailSender mailSender) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
        this.mailSender = mailSender;
    }

    public void handle(ForgotPasswordRequest request) {

        Optional<Guest> user = this.userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        String forgotToken = jwtGenerator.generateSimpleToken();

        // Save in Redis
        tokenRepository.save(new Token(
                user.get().getId(),
                forgotToken,
                new Date(System.currentTimeMillis() + 15 * 60 * 1000).getTime()));

        Map<String, Object> options = new HashMap<>();

        options.put("name", user.get().getEmail());
        options.put("forgotLink",
                "localhost:8080/changepassword?token=" + forgotToken);

        mailSender.sendEmail(
                "Pedido de esquecimento de senha",
                request.getEmail(),
                "forgotpassword",
                options);

    }
}
