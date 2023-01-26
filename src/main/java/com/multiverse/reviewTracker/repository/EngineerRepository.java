package com.multiverse.reviewTracker.repository;

import com.multiverse.reviewTracker.model.Engineer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer,Long> {


}
