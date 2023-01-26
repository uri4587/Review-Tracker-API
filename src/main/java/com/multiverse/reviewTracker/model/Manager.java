package com.multiverse.reviewTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "manager")

public class Manager extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "manager_engineer",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "engineer_id"))
    @JsonIgnore
    public List<Engineer> engineers = new ArrayList<>();

    @OneToMany(mappedBy = "manager")
    private List<Feedback> feedbacks;
    public Manager() {
        this.engineers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public List<Engineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(List<Engineer> engineers) {
        this.engineers = engineers;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        return super.toString() + "Engineers" + engineers.stream()
                .map(engineer -> engineer.getFirstName() + " " + engineer.getLastName() + " " + engineer.getId())
                .collect(Collectors.joining(", "));

//                "Feedbacks" + feedbacks.stream()
//                .map(feedback -> feedback.getDescription() + "<---- Description " + feedback.getId() + "<---- Id")
//                .collect(Collectors.joining(", "));
    }
}
