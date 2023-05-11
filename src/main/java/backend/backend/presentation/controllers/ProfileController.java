package backend.backend.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.GetProfileService;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.worker.WorkerResponse;
import backend.backend.presentation.mappers.GuestMapper;

@RequestMapping("/profile")
@RestController
public class ProfileController {

    @Autowired
    private GetProfileService getProfileService;

    @GetMapping()
    public ResponseEntity<WorkerResponse> profile() {

        Guest profile = getProfileService.handle();

        return ResponseEntity.ok().body(
                GuestMapper.INSTANCE.toWorkerResponse(profile));

    }

}
