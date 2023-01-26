package com.multiverse.reviewTracker.controller;

import com.multiverse.reviewTracker.dto.CheckpointRequestDTO;
import com.multiverse.reviewTracker.dto.CheckpointResponseDTO;
import com.multiverse.reviewTracker.model.Checkpoint;
import com.multiverse.reviewTracker.repository.CheckpointRepository;
import com.multiverse.reviewTracker.service.CheckpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkpoints")
public class CheckpointController {

    private final CheckpointService checkpointService;
    private final CheckpointRepository checkpointRepository;

    @Autowired
    public CheckpointController(CheckpointService checkpointService,
                                CheckpointRepository checkpointRepository) {
        this.checkpointService = checkpointService;
        this.checkpointRepository = checkpointRepository;
    }

    @GetMapping()
    public ResponseEntity<List<CheckpointResponseDTO>> getCheckpoints() {
        List<CheckpointResponseDTO> checkpoints = checkpointService.getCheckpoints();

        return new ResponseEntity<>(checkpoints, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckpointResponseDTO> getCheckpoint(@PathVariable("id") Long id) {
        Checkpoint checkpoint = checkpointService.getCheckpoint(id);
        CheckpointResponseDTO checkpointResponseDTO = new CheckpointResponseDTO(checkpoint);
        return new ResponseEntity<>(checkpointResponseDTO, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<CheckpointResponseDTO> createCheckpoint(@RequestBody CheckpointRequestDTO checkpointRequestDTO) {
        Checkpoint checkpoint = checkpointService.createCheckpoint(
                checkpointRequestDTO.getDescription(),
                checkpointRequestDTO.getIsComplete(),
                checkpointRequestDTO.getFeedbackId()
        );

        CheckpointResponseDTO checkpointResponseDTO = new CheckpointResponseDTO(checkpoint);
        return new ResponseEntity<>(checkpointResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CheckpointResponseDTO> deleteCheckpoint(@PathVariable Long id){
        Checkpoint checkpoint = checkpointService.getCheckpoint(id);
        checkpointService.deleteCheckpoint(id);

        CheckpointResponseDTO checkpointResponseDTO = new CheckpointResponseDTO(checkpoint);
        return new ResponseEntity<>(checkpointResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckpointResponseDTO> updateCheckpoint(@PathVariable Long id, @RequestBody CheckpointRequestDTO checkpointRequestDTO) {
        Checkpoint checkpointToUpdate = checkpointService.updateCheckpoint(id, checkpointRequestDTO);
        CheckpointResponseDTO checkpointResponseDTO = new CheckpointResponseDTO(checkpointToUpdate);
        return new ResponseEntity<>(checkpointResponseDTO, HttpStatus.OK);
    }
}
