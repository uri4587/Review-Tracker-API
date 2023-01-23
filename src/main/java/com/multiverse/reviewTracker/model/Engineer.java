package com.multiverse.reviewTracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Getter
//@Setter
@Entity
@Table(name = "engineer")
public class Engineer extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch= FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            },
            mappedBy = "engineers")
    public List<Manager> managers = new ArrayList<>();


    public Engineer() {
        this.managers = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }
}
