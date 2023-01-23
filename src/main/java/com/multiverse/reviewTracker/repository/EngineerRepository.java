package com.multiverse.reviewTracker.repository;

import com.multiverse.reviewTracker.model.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer,Long> {
}
