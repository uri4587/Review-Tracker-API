package com.multiverse.reviewTracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@DiscriminatorValue("manager")
public class Manager extends User {
    @Column(name ="subordinates", nullable = true)
    public ArrayList<Engineer> subordinates;
}
