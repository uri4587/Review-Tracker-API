package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ManagerRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Engineer> engineers;

    public ManagerRequestDTO(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.engineers = new ArrayList<>();
    }
}
