package com.eddie.uspsaMatchParser.repository;

import com.eddie.uspsaMatchParser.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {
    boolean existsByMatchNameAndMatchDate(String matchName, LocalDate matchDate);
}
