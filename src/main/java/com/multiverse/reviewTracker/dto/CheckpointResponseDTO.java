package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Checkpoint;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CheckpointResponseDTO {
    private Long id;
    private String description;
    private Boolean isComplete;
    private Date createdAt;
    private Date updatedAt;
    private FeedbackResponseDTO feedback;

    public CheckpointResponseDTO(Checkpoint checkpoint) {
        this.id = checkpoint.getId();
        this.description = checkpoint.getDescription();
        this.isComplete = checkpoint.getIsComplete();
        this.createdAt = checkpoint.getCreatedAt();
        this.updatedAt = checkpoint.getUpdatedAt();
        this.feedback = new FeedbackResponseDTO(checkpoint.getFeedback());
    }
}
