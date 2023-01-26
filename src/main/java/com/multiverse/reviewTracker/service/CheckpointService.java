package com.multiverse.reviewTracker.service;

import com.multiverse.reviewTracker.dto.*;
import com.multiverse.reviewTracker.exception.ResourceNotFoundException;
import com.multiverse.reviewTracker.model.Checkpoint;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Feedback;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.repository.CheckpointRepository;
import com.multiverse.reviewTracker.repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckpointService {
    private final CheckpointRepository checkpointRepository;
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public CheckpointService(CheckpointRepository checkpointRepository,
                             FeedbackRepository feedbackRepository) {this.checkpointRepository = checkpointRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public List<CheckpointResponseDTO> getCheckpoints(){
        return checkpointRepository.findAll().stream()
                .map(CheckpointResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Checkpoint getCheckpoint(Long id) {
        return checkpointRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Checkpoint not found"));
    }

    @Transactional
    public Checkpoint createCheckpoint(String description,
                                                  Boolean isComplete,
                                                  Long feedbackId) {

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));

        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setDescription(description);
        checkpoint.setIsComplete(isComplete);
        checkpoint.setFeedback(feedback);

        feedback.getCheckpoints().add(checkpoint);

       return checkpointRepository.save(checkpoint);

    }

    @Transactional
    public Checkpoint deleteCheckpoint(Long id) {

        Checkpoint checkpoint = checkpointRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Checkpoint not found with id: " + id));
        checkpointRepository.delete(checkpoint);
        return checkpoint;
    }

    @Transactional
    public void deleteAll(){
        checkpointRepository.deleteAll();
    }

    @Transactional
    public Checkpoint updateCheckpoint(Long id, CheckpointRequestDTO checkpointRequestDTO) {
        Checkpoint checkpoint = checkpointRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Checkpoint not found with id:" + id));

        checkpoint.setDescription(checkpointRequestDTO.getDescription());
        checkpoint.setIsComplete(checkpointRequestDTO.getIsComplete());

        return checkpointRepository.save(checkpoint);
    }
}
