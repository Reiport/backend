package backend.backend.services;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import backend.backend.application.common.interfaces.repositories.ICountryRepository;
import backend.backend.application.common.interfaces.repositories.IGuestTypeRepository;
import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.LoginService;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.domain.entities.Country;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.GuestType;
import backend.backend.domain.entities.PostalCode;
import backend.backend.presentation.contracts.authentication.LoginRequest;
import backend.backend.presentation.errors.authentication.PasswordDontMatchException;
import backend.backend.presentation.errors.authentication.UserNotFoundException;

@ActiveProfiles("test")
@DisplayName("Login Use Case Testing")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private ICountryRepository countryRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostalCodeRepository postRepository;

    @Autowired
    private IGuestTypeRepository guestTypeRepository;

    @Autowired
    private LoginService sut;

    @BeforeEach()
    public void generateNecessaryData() {

        Country portugal = countryRepository.save(new Country("Portugal"));

        GuestType guestType = guestTypeRepository.save(new GuestType("Cliente"));

        PostalCode postalCode = postRepository.save(
                new PostalCode(
                        "4200-014",
                        "There is no Description or what so ever",
                        "Porto",
                        portugal));

        this.userRepository.save(
                new Guest(
                        "bpeyto5j@opensource.org",
                        "$2b$12$cWwwtTihsnxj4d5ckjIEcew4POx3O7CrPL9NfYFQu1Qi0eWhREj46",
                        "Barbara-anne",
                        "Peyto",
                        LocalDate.of(1956, Month.AUGUST, 6),
                        "533907927",
                        "Hoffman",
                        1,
                        "429319530",
                        postalCode,
                        guestType));
    }

    @AfterEach()
    void tearDown() {
        userRepository.deleteAll();
    }

    private AuthenticationResult makeSut(String email, String password) {
        return sut.handle(new LoginRequest(
                email,
                password));
    }

    @DisplayName("It should not allow login an user that does not exist")
    @Test
    public void testLogin() {

        assertThrows(
                UserNotFoundException.class,
                () -> makeSut(
                        "ricardo@gmail.com",
                        "password"));

    }

    @DisplayName("It should not allow login if password do not match")
    @Test
    public void testPasswordMatch() {
        assertThrows(
                PasswordDontMatchException.class,
                () -> makeSut(
                        "bpeyto5j@opensource.org",
                        ""));
    }

    @DisplayName("It should return tokens when authenticated")
    @Test
    public void testTokens() {
        assertInstanceOf(
                AuthenticationResult.class,
                makeSut(
                        "bpeyto5j@opensource.org",
                        "wv5Q5VRC"));
    }

}
