package com.multiverse.reviewTracker.controller;


import com.multiverse.reviewTracker.dto.FeedbackRequestDTO;
import com.multiverse.reviewTracker.dto.FeedbackResponseDTO;
import com.multiverse.reviewTracker.model.Feedback;
import com.multiverse.reviewTracker.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private final FeedbackService feedbackService;
    private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);
    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping()
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback() {
        List<FeedbackResponseDTO> feedbacks = feedbackService.getAllFeedback();

        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> getFeedback(@PathVariable("id") Long id) {
        Feedback feedback = feedbackService.getFeedback(id);
        FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO(feedback);
        return new ResponseEntity<>(feedbackResponseDTO, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<FeedbackResponseDTO> createFeedback(@RequestBody FeedbackRequestDTO requestDTO) {
//
        Feedback feedback = feedbackService.createFeedback(
                requestDTO.getDescription(),
                requestDTO.getEngineerId(),
                requestDTO.getManagerId()
        );

        FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO(feedback);
//
        return new ResponseEntity<>(feedbackResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> deleteFeedback(@PathVariable Long id){
        Feedback feedback = feedbackService.getFeedback(id);
        feedbackService.deleteFeedback(id);

        FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO(feedback);
        return new ResponseEntity<>(feedbackResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> updateCheckpoint(@PathVariable Long id, @RequestBody FeedbackRequestDTO feedbackRequestDTO) {
        Feedback feedbackToUpdate = feedbackService.updateFeedback(id, feedbackRequestDTO);
        FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO(feedbackToUpdate);
        return new ResponseEntity<>(feedbackResponseDTO, HttpStatus.OK);
    }
}
