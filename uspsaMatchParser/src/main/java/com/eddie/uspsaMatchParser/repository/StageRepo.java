package com.eddie.uspsaMatchParser.repository;

import com.eddie.uspsaMatchParser.models.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepo extends JpaRepository<Stage, Integer> {
}
