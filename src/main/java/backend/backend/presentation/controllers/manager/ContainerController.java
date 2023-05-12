package backend.backend.presentation.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.application.services.worker.manager.GetContainerByIdService;
import backend.backend.domain.entities.Container;
import backend.backend.presentation.contracts.container.ContainerResponse;
import backend.backend.presentation.mappers.ContainerMapper;

@RequestMapping("/containers")
@RestController
public class ContainerController {

    @Autowired
    private GetContainerByIdService getContainerByIdService;

    @PreAuthorize("hasAuthority('Gestor')")
    @GetMapping()
    public ResponseEntity<ContainerResponse> getContainerById(@RequestParam String license) {

        Container container = getContainerByIdService.handle(license);

        return ResponseEntity.ok(ContainerMapper.INSTANCE.toContainerResponse(container));
    }

}
