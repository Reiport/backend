package backend.backend.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import backend.backend.application.services.authentication.ForgotPasswordService;
import backend.backend.presentation.contracts.authentication.ForgotPasswordRequest;
import backend.backend.presentation.errors.authentication.UserNotFoundException;
import backend.context.SpringTestContext;

@ActiveProfiles("test")
@DisplayName("ForgotPassword Use Case Testing")
@ExtendWith(MockitoExtension.class)
@Import(SpringTestContext.class)
@SpringBootTest
public class ForgotPasswordServiceTest {

    @Autowired
    private ForgotPasswordService sut;

    private void makeSut(String email) {
        sut.handle(
                new ForgotPasswordRequest(
                        email));
    }

    @DisplayName("It should not allow send an forgotpassword request if user email don't exist")
    @Test
    public void testLogin() {

        assertThrows(
                UserNotFoundException.class,
                () -> makeSut("diogo@gmail.com"));

    }

}
