package com.multiverse.reviewTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @JsonIgnore
    public List<Manager> managers = new ArrayList<>();

    @OneToMany(mappedBy = "engineer")
    private List<Feedback> feedbacks;

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

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        return super.toString() + "Managers" + managers.stream()
                .map(manager -> manager.getFirstName() + " " + manager.getLastName() + " " + manager.getId())
                .collect(Collectors.joining(", ")) ;

//        "Feedbacks" + feedbacks.stream()
//                .map(feedback -> feedback.getDescription() + "<---- Description " + feedback.getId() + "<---- Id")
//                .collect(Collectors.joining(", "))
    }

}
