package com.eddie.uspsaMatchParser.repository;

import com.eddie.uspsaMatchParser.models.StageScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageScoreRepo extends JpaRepository<StageScore, Integer> {
}
