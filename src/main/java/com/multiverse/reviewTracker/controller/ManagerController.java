package com.multiverse.reviewTracker.controller;

import com.multiverse.reviewTracker.dto.EngineerRequestDTO;
import com.multiverse.reviewTracker.dto.EngineerResponseDTO;
import com.multiverse.reviewTracker.dto.ManagerRequestDTO;
import com.multiverse.reviewTracker.dto.ManagerResponseDTO;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.service.EngineerService;
import com.multiverse.reviewTracker.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping()
    public ResponseEntity<List<ManagerResponseDTO>> getManagers() {
        List<Manager> managers = managerService.getManagers();
        List<ManagerResponseDTO> managerResponseDTOS = managers.stream()
                .map(manager -> new ManagerResponseDTO(manager))
                .collect(Collectors.toList());
        return new ResponseEntity<>(managerResponseDTOS, HttpStatus.OK);
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
    public ResponseEntity<Void> createManagerEngineerJoin(
            @PathVariable Long managerId,
            @PathVariable Long engineerId ) {
        managerService.createManagerEngineerJoin(managerId, engineerId);
        return new ResponseEntity<>(HttpStatus.CREATED);

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
