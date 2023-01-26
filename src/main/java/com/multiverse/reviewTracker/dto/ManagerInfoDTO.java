package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerInfoDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public ManagerInfoDTO(Manager manager) {
        this.id = manager.getId();
        this.firstName = manager.getFirstName();
        this.lastName = manager.getLastName();
    }
}
