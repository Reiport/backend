package backend.backend.presentation.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.authentication.ForgotPasswordService;
import backend.backend.application.services.authentication.LoginService;
import backend.backend.application.services.authentication.RegisterUserService;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.presentation.contracts.authentication.ForgotPasswordRequest;
import backend.backend.presentation.contracts.authentication.LoginRequest;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

        private final RegisterUserService registerService;
        private final LoginService loginService;
        private final ForgotPasswordService forgotPasswordService;

        @PostMapping("/register")
        private ResponseEntity<AuthenticationResult> register(@Valid @RequestBody RegisterRequest request) {

                var tokens = this.registerService.handle(new RegisterRequest(
                                request.getEmail(),
                                request.getPassword()));

                return new ResponseEntity<AuthenticationResult>(
                                tokens,
                                HttpStatusCode.valueOf(201));

        }

        @PostMapping("/login")
        private ResponseEntity<AuthenticationResult> login(@Valid @RequestBody LoginRequest request) {

                var tokens = this.loginService.handle(new LoginRequest(
                                request.getEmail(),
                                request.getPassword()));

                return new ResponseEntity<AuthenticationResult>(
                                tokens,
                                HttpStatusCode.valueOf(200));

        }

        @PostMapping("/forgotpassword")
        private ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {

                this.forgotPasswordService.handle(request);

                return new ResponseEntity<>(
                                null,
                                HttpStatusCode.valueOf(200));

        }

}