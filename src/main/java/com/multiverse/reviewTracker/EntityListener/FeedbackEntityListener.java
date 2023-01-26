package com.multiverse.reviewTracker.EntityListener;

import com.multiverse.reviewTracker.model.Feedback;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class FeedbackEntityListener {
    @PrePersist
    public void onPrePersist(Feedback feedback) {
        feedback.setCreatedAt(new Date());
        feedback.setUpdatedAt(new Date());
    }

    @PreUpdate
    public void onPreUpdate(Feedback feedback) {
        feedback.setUpdatedAt(new Date());
    }
}
