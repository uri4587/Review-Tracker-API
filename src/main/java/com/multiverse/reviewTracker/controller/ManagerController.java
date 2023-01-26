package com.multiverse.reviewTracker.controller;

import com.multiverse.reviewTracker.dto.ManagerRequestDTO;
import com.multiverse.reviewTracker.dto.ManagerResponseDTO;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.repository.EngineerRepository;
import com.multiverse.reviewTracker.service.EngineerService;
import com.multiverse.reviewTracker.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    private final ManagerService managerService;
    private final EngineerService engineerService;


    @Autowired
    public ManagerController(ManagerService managerService,
                             EngineerService engineerService, EngineerRepository engineerRepository) {
        this.managerService = managerService;
        this.engineerService = engineerService;

    }

    @GetMapping()
    public ResponseEntity<List<ManagerResponseDTO>> getManagers() {
        List<ManagerResponseDTO> managers = managerService.getManagers();

        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponseDTO> getManagerById(@PathVariable("id") Long id){
        Manager manager = managerService.getManager(id);
        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO(manager);
        return new ResponseEntity<>(managerResponseDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ManagerResponseDTO> createManager(@RequestBody ManagerRequestDTO managerRequestDTO) {
        Manager manager = managerService.createManager(
                managerRequestDTO.getFirstName(),
                managerRequestDTO.getLastName(),
                managerRequestDTO.getEmail(),
                managerRequestDTO.getPassword()

        );
        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO(manager);
        return new ResponseEntity<>(managerResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/manager/{managerId}/engineer/{engineerId}")
    public ResponseEntity<String> createManagerEngineerJoin(
            @PathVariable Long managerId,
            @PathVariable Long engineerId ) {
        Manager manager = managerService.getManager(managerId);
        Engineer engineer = engineerService.getEngineer(engineerId);
        manager.getEngineers().add(engineer);
        engineer.getManagers().add(manager);
        managerService.createManagerEngineerJoin(managerId, engineerId);
        return new ResponseEntity<>("Engineer and Manager successfully joined", HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ManagerResponseDTO> deleteManager(@PathVariable Long id){
        Manager manager = managerService.getManager(id);
        managerService.deleteManager(id);
        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO(manager);
        return new ResponseEntity<>(managerResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerResponseDTO> updateManager(@PathVariable Long id, @RequestBody ManagerRequestDTO managerRequestDTO) {
        Manager updatedManager = managerService.updateManager(id, managerRequestDTO);
        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO(updatedManager);
        return new ResponseEntity<>(managerResponseDTO, HttpStatus.OK);
    }
}
