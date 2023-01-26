package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Checkpoint;
import com.multiverse.reviewTracker.model.Feedback;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FeedbackResponseDTO {
    private Long id;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Long engineerId;
    private Long managerId;

    private List checkpoints;

    public FeedbackResponseDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.description = feedback.getDescription();
        this.engineerId = feedback.getEngineer().getId();
        this.managerId = feedback.getManager().getId();
        this.createdAt = feedback.getCreatedAt();
        this.updatedAt = feedback.getUpdatedAt();
//        this.checkpoints = new ArrayList<>();
//        for(Checkpoint checkpoint : feedback.getCheckpoints()) {
//            this.checkpoints.add(new CheckpointResponseDTO(checkpoint));
//        }
    }
}