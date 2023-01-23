package com.multiverse.reviewTracker.repository;

import com.multiverse.reviewTracker.model.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineerRepository extends JpaRepository<Engineer,Long> {
}
