package backend.backend.presentation.controllers.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.authentication.ForgotPasswordService;
import backend.backend.application.services.authentication.LoginService;
import backend.backend.application.services.authentication.RegisterUserService;
import backend.backend.application.services.authentication.ValidateAccountService;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.presentation.contracts.authentication.ForgotPasswordRequest;
import backend.backend.presentation.contracts.authentication.LoginRequest;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private RegisterUserService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private ValidateAccountService validateAccountService;

    // Only for clients
    @PostMapping("/register")
    private ResponseEntity<AuthenticationResult> register(@Valid @RequestBody RegisterRequest request) {

        var tokens = this.registerService.handle(new RegisterRequest(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthDate(),
                request.getNif(),
                request.getStreet(),
                request.getPort(),
                request.getTelephone(),
                request.getPostalCode(),
                "Cliente"));

        return ResponseEntity
                .ok()
                .body(tokens);

    }

    @PostMapping("/login")
    private ResponseEntity<AuthenticationResult> login(@Valid @RequestBody LoginRequest request) {

        var tokens = this.loginService.handle(new LoginRequest(
                request.getEmail(),
                request.getPassword()));

        return ResponseEntity
                .ok()
                .body(tokens);

    }

    @PostMapping("/forgotpassword")
    private ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {

        this.forgotPasswordService.handle(request);

        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/validate_account")
    private ResponseEntity<?> validate_account(@RequestParam String token) {

        validateAccountService.handle(token);

        return ResponseEntity
                .ok()
                .build();

    }

}