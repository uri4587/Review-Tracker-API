package com.multiverse.reviewTracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@DiscriminatorValue("engineer")
public class Engineer extends User{
    @Column
    public ArrayList<Manager> managers;

    public Engineer() {
    }
}
