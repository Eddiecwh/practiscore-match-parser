package com.eddie.uspsaMatchParser.repository;

import com.eddie.uspsaMatchParser.models.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepo extends JpaRepository<Competitor, Integer> {

}
