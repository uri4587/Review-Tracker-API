package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Manager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EngineerRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Manager> managers;

    public EngineerRequestDTO(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.managers = new ArrayList<>();
    }
}
