package com.multiverse.reviewTracker.dto;

import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Feedback;
import com.multiverse.reviewTracker.model.Manager;
import lombok.Getter;
import lombok.Setter;;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class ManagerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<EngineerInfoDTO> engineers;

//    private List<EngineerResponseDTO> engineers;

    public ManagerResponseDTO(Manager manager) {
        this.id = manager.getId();
        this.firstName = manager.getFirstName();
        this.lastName = manager.getLastName();
        this.email = manager.getEmail();
        this.password = manager.getPassword();
        this.engineers = new ArrayList<>();
        for(Engineer engineer: manager.getEngineers()) {
            this.engineers.add(new EngineerInfoDTO(engineer));
        }

    }

}
