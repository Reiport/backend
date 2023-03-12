package backend.backend.application.services.authentication;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.ITokenRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.common.ResetPassword;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.redis.Token;
import backend.backend.presentation.errors.authentication.PasswordsNotEqualException;
import backend.backend.presentation.errors.authentication.TokenNotFoundException;
import backend.backend.presentation.errors.authentication.UserDontOwnTokenException;

@Service
public class ResetPasswordService {

    private final IUserRepository userRepository;
    private final ITokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(
            IUserRepository userRepository,
            ITokenRepository tokenRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void handle(ResetPassword request) {

        Guest currentUser;

        // get token
        Optional<Token> tokenFound = tokenRepository.findByValue(request.getToken());

        if (tokenFound.isEmpty()) {
            throw new TokenNotFoundException();
        }

        Optional<Guest> userFound = userRepository.findById(tokenFound.get().getUserId());

        if (userFound.isEmpty()) {
            tokenRepository.delete(tokenFound.get());
            throw new UserDontOwnTokenException();
        }

        currentUser = userFound.get();

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordsNotEqualException();
        }

        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(currentUser);

        tokenRepository.delete(tokenFound.get());

    }

}
