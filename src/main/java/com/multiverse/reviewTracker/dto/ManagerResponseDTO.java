package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import lombok.Getter;
import lombok.Setter;;

import java.util.ArrayList;

@Getter
@Setter
public class ManagerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Engineer> subordinates;

    public ManagerResponseDTO(Manager manager) {
        this.id = manager.getId();
        this.firstName = manager.getFirstName();
        this.lastName = manager.getLastName();
        this.email = manager.getEmail();
        this.password = manager.getPassword();
    }

}
