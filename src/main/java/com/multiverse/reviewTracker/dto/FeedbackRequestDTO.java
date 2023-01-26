package com.multiverse.reviewTracker.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FeedbackRequestDTO {

    private String description;
    private Long engineerId;
    private Long managerId;

    @JsonCreator
    public FeedbackRequestDTO(@JsonProperty("description") String description,
                              @JsonProperty("engineer_id") Long engineerId,
                              @JsonProperty("manager_id") Long managerId) {

        this.description = description;
        this.engineerId = engineerId;
        this.managerId = managerId;


    }
}
