package backend.backend.presentation.controllers.manager;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.TruckService;
import backend.backend.domain.entities.Vehicle;
import backend.backend.presentation.contracts.vehicle.TruckRequest;
import backend.backend.presentation.contracts.vehicle.TruckResponse;
import backend.backend.presentation.mappers.TruckMapper;
import jakarta.validation.Valid;

@RequestMapping("/trucks")
@RestController
public class TruckController {

    @Autowired
    private TruckService truckService;

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping()
    public ResponseEntity<TruckResponse> getTruckById(@RequestParam String license) {

        Vehicle truck = truckService.getTruckById(license);

        return ResponseEntity.ok(TruckMapper.INSTANCE.ToTruckResponse(truck));
    }

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping("/")
    public ResponseEntity<Collection<TruckResponse>> getTrucks() {

        Collection<Vehicle> result = truckService.getAllTrucks();

        return ResponseEntity.ok(result.stream()
                .map(vehicle -> TruckMapper.INSTANCE.ToTruckResponse(vehicle))
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('Gestor')")
    @PostMapping("/create")
    public ResponseEntity<String> createTruck(@Valid @RequestBody TruckRequest request) {
        truckService.createTruck(request);
        return ResponseEntity.ok().body("O veiculo foi adicionado");
    }

    @PreAuthorize("hasAuthority('Gestor')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTruck(@RequestParam String license) {
        truckService.deleteTruck(license);
        return ResponseEntity.ok().body("O veiculo foi removido");
    }

}
