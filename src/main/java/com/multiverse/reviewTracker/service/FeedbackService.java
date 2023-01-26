package com.multiverse.reviewTracker.service;


import com.multiverse.reviewTracker.controller.FeedbackController;
import com.multiverse.reviewTracker.dto.FeedbackRequestDTO;
import com.multiverse.reviewTracker.dto.FeedbackResponseDTO;
import com.multiverse.reviewTracker.exception.ResourceNotFoundException;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Feedback;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.repository.EngineerRepository;
import com.multiverse.reviewTracker.repository.FeedbackRepository;
import com.multiverse.reviewTracker.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ManagerRepository managerRepository;
    private final EngineerRepository engineerRepository;

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository,
                           ManagerRepository managerRepository,
                           EngineerRepository engineerRepository) {this.feedbackRepository = feedbackRepository;
        this.managerRepository = managerRepository;
        this.engineerRepository = engineerRepository;
    }

    @Transactional
    public List<FeedbackResponseDTO> getAllFeedback(){
        return feedbackRepository.findAll().stream()
                .map(FeedbackResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Feedback getFeedback(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
    }

    @Transactional
    public Feedback createFeedback(String description, Long engineerId, Long managerId) {
        Engineer engineer = engineerRepository.findById(engineerId)
                .orElseThrow(()-> new ResourceNotFoundException("Engineer not found"));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(()-> new ResourceNotFoundException("Manager not found"));

        Feedback feedback = new Feedback();
        feedback.setDescription(description);
        feedback.setEngineer(engineer);
        feedback.setManager(manager);

        manager.getFeedbacks().add(feedback);
        engineer.getFeedbacks().add(feedback);


        return feedbackRepository.save(feedback);


    }

    @Transactional
    public Feedback deleteFeedback(Long id) {

        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Feedback not found with id: " + id));

        feedbackRepository.delete(feedback);
        return feedback;
    }

    @Transactional
    public void deleteAll(){
        feedbackRepository.deleteAll();
    }

    @Transactional
    public Feedback updateFeedback(Long id, FeedbackRequestDTO feedbackRequestDTO) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Feedback not found with id:" + id));

        feedback.setDescription(feedbackRequestDTO.getDescription());


        return feedbackRepository.save(feedback);
    }
}
