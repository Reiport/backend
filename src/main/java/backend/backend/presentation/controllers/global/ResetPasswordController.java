package backend.backend.presentation.controllers.global;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import backend.backend.application.services.authentication.ResetPasswordService;
import backend.backend.application.services.authentication.common.ResetPassword;
import backend.backend.presentation.contracts.SimpleResponse;
import backend.backend.presentation.contracts.authentication.ResetPasswordRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PostMapping("/resetpassword")
    private ResponseEntity<SimpleResponse> resetPassword(@RequestParam String token,
            @Valid @RequestBody ResetPasswordRequest request) {

        this.resetPasswordService.handle(
                new ResetPassword(
                        request.getPassword(),
                        request.getConfirmPassword(),
                        token));

        return ResponseEntity
                .ok(new SimpleResponse("Palavra-passe redefinida com sucesso!"));

    }

}
