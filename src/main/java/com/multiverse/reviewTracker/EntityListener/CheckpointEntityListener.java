package com.multiverse.reviewTracker.EntityListener;

import com.multiverse.reviewTracker.model.Checkpoint;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class CheckpointEntityListener {
    @PrePersist
    public void onPrePersist(Checkpoint checkpoint) {
        checkpoint.setCreatedAt(new Date());
        checkpoint.setUpdatedAt(new Date());
    }

    @PreUpdate
    public void onPreUpdate(Checkpoint checkpoint) {
        checkpoint.setUpdatedAt(new Date());
    }
}
