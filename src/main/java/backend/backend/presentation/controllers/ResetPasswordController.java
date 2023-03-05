package backend.backend.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import backend.backend.application.services.authentication.ResetPasswordService;
import backend.backend.application.services.authentication.common.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PostMapping("/resetpassword")
    private ResponseEntity<?> resetPassword(@RequestParam String token,
            @RequestBody ResetPasswordRequest request) {

        this.resetPasswordService.handle(
                new ResetPasswordRequest(
                        request.getPassword(),
                        request.getConfirmPassword(),
                        token));

        return ResponseEntity.ok().build();

    }

}