package backend.backend.presentation.controllers.manager;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.worker.manager.GetAllTrucksService;
import backend.backend.application.services.worker.manager.GetTruckByIdService;
import backend.backend.domain.entities.Vehicle;
import backend.backend.presentation.contracts.vehicle.TruckResponse;
import backend.backend.presentation.mappers.TruckMapper;

@RequestMapping("/trucks")
@RestController
public class TruckController {

    @Autowired
    private GetTruckByIdService getTruckByIdService;

    @Autowired
    private GetAllTrucksService getAllTrucksService;

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping()
    public ResponseEntity<TruckResponse> getTruckById(@RequestParam String license) {

        Vehicle truck = getTruckByIdService.handle(license);

        return ResponseEntity.ok(TruckMapper.INSTANCE.ToTruckResponse(truck));
    }

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping("/")
    public ResponseEntity<Collection<TruckResponse>> getTrucks() {

        Collection<Vehicle> result = getAllTrucksService.handle();

        return ResponseEntity.ok(result.stream()
                .map(vehicle -> TruckMapper.INSTANCE.ToTruckResponse(vehicle))
                .collect(Collectors.toList()));
    }

}
