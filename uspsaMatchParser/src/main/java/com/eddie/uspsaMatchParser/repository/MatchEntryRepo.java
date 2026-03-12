package com.eddie.uspsaMatchParser.repository;

import com.eddie.uspsaMatchParser.models.MatchEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchEntryRepo extends JpaRepository<MatchEntry, Integer> {
}
