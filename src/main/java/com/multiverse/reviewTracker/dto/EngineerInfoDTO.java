package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Engineer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineerInfoDTO {

    private Long id;
    private String firstName;
    private String lastName;

    public EngineerInfoDTO(Engineer engineer) {
        this.id= engineer.getId();
        this.firstName = engineer.getFirstName();
        this.lastName = engineer.getLastName();
    }
}
