package com.eddie.uspsaMatchParser.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ParsedMatchResult {
    private Match match;
    private List<Stage> stages;
    private List<Competitor> competitors;
    private List<StageScore> stageScores;
    private List<MatchEntry> matchEntries;
}
