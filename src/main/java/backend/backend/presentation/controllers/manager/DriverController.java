package backend.backend.presentation.controllers.manager;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.worker.GetAllDriversService;
import backend.backend.domain.entities.Driver;
import backend.backend.presentation.contracts.worker.DriverResponse;
import backend.backend.presentation.mappers.GuestMapper;

@RequestMapping("/drivers")
@RestController
public class DriverController {

    @Autowired
    private GetAllDriversService getAllDriversService;

    public DriverResponse getDriverById() {
        return null;
    }

    @GetMapping("/")
    public ResponseEntity<Collection<DriverResponse>> getAllDrivers() {

        Collection<Driver> result = getAllDriversService.handle();

        return ResponseEntity.ok().body(result
                .stream()
                .map(driver -> GuestMapper.INSTANCE.toDriverResponse(driver))
                .collect(Collectors.toList()));

    }

}
