package backend.backend.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import backend.backend.application.common.interfaces.repositories.ICountryRepository;
import backend.backend.application.common.interfaces.repositories.IGuestTypeRepository;
import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.RegisterUserService;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.domain.entities.Country;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.GuestType;
import backend.backend.domain.entities.PostalCode;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import backend.backend.presentation.errors.authentication.UserAlreadyRegisteredException;
import backend.context.SpringTestContext;

@ActiveProfiles("test")
@DisplayName("Register Service Testing")
@ExtendWith(MockitoExtension.class)
@Import(SpringTestContext.class)
@SpringBootTest
public class RegisterServiceTest {

    @Autowired
    private ICountryRepository countryRepository;

    @Autowired
    private IPostalCodeRepository postRepository;

    @Autowired
    private IGuestTypeRepository guestTypeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private RegisterUserService sut;

    private Country portugal = new Country("Portugal");
    private GuestType guestType = new GuestType("Cliente");

    private PostalCode postalCode = new PostalCode(
            "4200-014",
            "There is no Description or what so ever",
            "Porto",
            portugal);

    private Guest guest = new Guest(
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
            guestType);

    private AuthenticationResult makeSut() {
        return sut.handle(new RegisterRequest(
                guest.getEmail(),
                guest.getPassword(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getBirthDate().toString(),
                guest.getNif(),
                guest.getStreet(),
                guest.getPort(),
                guest.getTelephone(),
                postalCode.getId(),
                guestType.getName()));
    }

    @BeforeEach()
    public void generateData() {
        countryRepository.save(portugal);
        guestTypeRepository.save(guestType);
        postRepository.save(postalCode);
        this.userRepository.save(guest);
    }

    @Test
    @DisplayName("It should not be able to register to users with the same email address")
    public void testEqualUsers() {

        this.userRepository.save(
                new Guest(
                        "kbillborough5i@engadget.com",
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

        assertThrows(
                UserAlreadyRegisteredException.class,
                () -> makeSut());

    }

}
