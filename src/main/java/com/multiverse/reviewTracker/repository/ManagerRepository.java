package com.multiverse.reviewTracker.repository;

import com.multiverse.reviewTracker.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}