package com.multiverse.reviewTracker.controller;

import com.multiverse.reviewTracker.dto.EngineerRequestDTO;
import com.multiverse.reviewTracker.dto.EngineerResponseDTO;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.service.EngineerService;
import com.multiverse.reviewTracker.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/engineers")
public class EngineerController {
    private final EngineerService engineerService;
    private final ManagerService managerService;

    @Autowired
    public EngineerController(EngineerService engineerService, ManagerService managerService) {
        this.engineerService = engineerService;
        this.managerService = managerService;
    }

    @GetMapping()
    public ResponseEntity<List<EngineerResponseDTO>> getEngineers() {

        List<EngineerResponseDTO> engineers = engineerService.getEngineers();

        return new ResponseEntity<>(engineers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EngineerResponseDTO> getEngineerById(@PathVariable("id") Long id){
        Engineer engineer = engineerService.getEngineer(id);
        EngineerResponseDTO engineerResponseDTO = new EngineerResponseDTO(engineer);
        return new ResponseEntity<>(engineerResponseDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EngineerResponseDTO> createEngineer(@RequestBody EngineerRequestDTO engineerRequest) {
        Engineer engineer = engineerService.createEngineer(
                engineerRequest.getFirstName(),
                engineerRequest.getLastName(),
                engineerRequest.getEmail(),
                engineerRequest.getPassword()
        );
        EngineerResponseDTO engineerResponseDTO = new EngineerResponseDTO(engineer);
        return new ResponseEntity<>(engineerResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EngineerResponseDTO> deleteEngineer(@PathVariable Long id){
        Engineer engineer = engineerService.getEngineer(id);
        engineerService.deleteEngineer(id);
        EngineerResponseDTO engineerResponseDTO = new EngineerResponseDTO(engineer);
        return new ResponseEntity<>(engineerResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EngineerResponseDTO> updateEngineer(@PathVariable Long id, @RequestBody EngineerRequestDTO engineerRequestDTO) {
        Engineer updatedEngineer = engineerService.updateEngineer(id, engineerRequestDTO);
        EngineerResponseDTO engineerResponseDTO = new EngineerResponseDTO(updatedEngineer);
        return new ResponseEntity<>(engineerResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/manager/{managerId}/engineer/{engineerId}")
    public ResponseEntity<Void> createManagerEngineerJoin(
            @PathVariable Long managerId,
            @PathVariable Long engineerId ) {
        Manager manager = managerService.getManager(managerId);
        Engineer engineer = engineerService.getEngineer(engineerId);
        manager.getEngineers().add(engineer);
        engineer.getManagers().add(manager);
        managerService.createManagerEngineerJoin(managerId, engineerId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
