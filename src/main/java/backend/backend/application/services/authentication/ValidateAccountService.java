package backend.backend.application.services.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.repositories.ITokenRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.redis.Token;
import backend.backend.presentation.errors.authentication.TokenNotFoundException;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@Service
public class ValidateAccountService {

    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private IUserRepository userRepository;

    public void handle(String token) {

        Token validation_token;
        Guest user;

        Optional<Token> tokenFound = tokenRepository.findByValue(token);

        if (tokenFound.isEmpty()) {
            throw new TokenNotFoundException();
        }

        validation_token = tokenFound.get();

        Optional<Guest> userFound = userRepository.findById(validation_token.getUserId());

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        user = userFound.get();
        user.setEnabled(true);

        userRepository.save(user);

    }

}
