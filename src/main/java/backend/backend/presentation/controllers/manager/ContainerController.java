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

import backend.backend.application.services.ContainerService;
import backend.backend.domain.entities.Container;
import backend.backend.presentation.contracts.container.ContainerRequest;
import backend.backend.presentation.contracts.container.ContainerResponse;
import backend.backend.presentation.mappers.ContainerMapper;
import jakarta.validation.Valid;

@RequestMapping("/containers")
@RestController
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping()
    public ResponseEntity<ContainerResponse> getContainerById(@RequestParam String license) {

        Container container = containerService.getContainerByLicense(license);

        return ResponseEntity.ok(ContainerMapper.INSTANCE.toContainerResponse(container));
    }

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping("/")
    public ResponseEntity<Collection<ContainerResponse>> getAllContainers() {

        Collection<Container> container = containerService.getAllContainers();

        return ResponseEntity
                .ok(container.stream().map(cont -> ContainerMapper.INSTANCE.toContainerResponse(cont))
                        .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('Gestor')")
    @PostMapping("/create")
    public ResponseEntity<String> createContainer(@Valid @RequestBody ContainerRequest request) {
        containerService.createContainer(request);

        return ResponseEntity.ok("O contentor foi adicionado");
    }

    @PreAuthorize("hasAuthority('Gestor')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteContentor(@RequestParam String license) {
        containerService.deleteContainer(license);

        return ResponseEntity.ok("O contentor foi removido");
    }

}
