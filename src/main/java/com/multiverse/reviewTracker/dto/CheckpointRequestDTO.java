package com.multiverse.reviewTracker.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CheckpointRequestDTO {

    private String description;
    private Boolean isComplete;
    private Long feedbackId;

    @JsonCreator
    public CheckpointRequestDTO(@JsonProperty("description") String description,
                                @JsonProperty("isComplete") Boolean isComplete,
                                @JsonProperty("feedback_id") Long feedbackId) {

        this.description = description;
        this.isComplete = isComplete;
        this.feedbackId = feedbackId;
    }
}
