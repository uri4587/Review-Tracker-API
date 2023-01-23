package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EngineerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Manager> managers;

    public EngineerResponseDTO(Engineer engineer) {
        this.id = engineer.getId();
        this.firstName = engineer.getFirstName();
        this.lastName = engineer.getLastName();
        this.email = engineer.getEmail();
        this.password = engineer.getPassword();
        this.managers = new ArrayList<>();
    }
}
