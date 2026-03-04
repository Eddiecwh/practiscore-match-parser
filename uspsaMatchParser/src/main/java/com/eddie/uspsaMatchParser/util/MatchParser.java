package com.eddie.uspsaMatchParser.util;

import com.eddie.uspsaMatchParser.models.*;
import com.eddie.uspsaMatchParser.repository.CompetitorRepo;
import com.eddie.uspsaMatchParser.repository.MatchRepo;
import com.eddie.uspsaMatchParser.repository.StageRepo;
import com.eddie.uspsaMatchParser.repository.StageScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MatchParser {
    Match match = new Match();
    List<Competitor> competitors = new ArrayList<>();
    List<Stage> stages = new ArrayList<>();
    List<StageScore> stageScores = new ArrayList<>();
    Map<Integer, Competitor> competitorMap = new HashMap<>();
    Map<Integer, Stage> stageMap = new HashMap<>();

    public ParsedMatchResult parseMatchData(String file) {
        ParsedMatchResult result = new ParsedMatchResult();

        String[] lines = file.split("\n");

        for (String line : lines) {
            if (line.startsWith("$INFO ")) {
                parseMatch(line);
            }

            if (line.startsWith("E ")) {
                parseCompetitor(line);
            }

            if (line.startsWith("G ")) {
                parseStage(line);
            }

            if (line.startsWith("I ")) {
                parseStageScore(line);
            }
        }

        result.setMatch(match);
        result.setStages(stages);
        result.setCompetitors(competitors);
        result.setStageScores(stageScores);

        return result;
    }

    private void parseMatch(String line) {
        if (line.startsWith("$INFO Region:")) {
            String region = line.split(":")[1].trim();
            System.out.println(region);
            MatchType matchType = MatchType.valueOf(region);
            match.setMatchType(matchType);
        }

        if (line.startsWith("$INFO Match name:")) {
            String name = line.split(":")[1].trim();
            match.setMatchName(name);
        }

        if (line.startsWith("$INFO Match date:")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(line.split(":")[1].trim(), formatter);
            match.setMatchDate(date);
        }

        if (line.startsWith("$INFO Club Name:")) {
            String clubName = line.split(":")[1].trim();
            match.setClubName(clubName);
        }

        if (line.startsWith("$INFO Match Level:")) {
            String matchLevel = line.split(":")[1].trim();
            match.setMatchLevel(matchLevel);
        }

        // Match info
        if (line.startsWith("$INFO Match Level:")) {
            String matchLevel = line.split(":")[1].trim();
            match.setMatchLevel(matchLevel);
        }
    }

    private void parseCompetitor(String line) {
        String[] parts = line.split(",");
        int sequenceNumber = Integer.parseInt(parts[0].replace("E ", "").trim());

        Competitor competitor = new Competitor();

        competitor.setFirstName(parts[2].trim());
        competitor.setLastName(parts[3].trim());
        competitor.setUspsaMemberNumber(parts[1].trim());


        competitors.add(competitor);
        competitorMap.put(sequenceNumber, competitor);
    }

    private void parseStage(String line) {
        String[] parts = line.split(",");
        Stage stage = new Stage();

        int stageSequence = Integer.parseInt(parts[0].replace("G ", "").trim());

        stage.setStageNo(stageSequence);
        stage.setRoundCount(Integer.parseInt(parts[2]));
        stage.setStagePts(Integer.parseInt(parts[3]));

        stage.setClassifier(parts[4].trim().equals("Yes"));
        stage.setClassifierName(parts[5]);
        stage.setStageName(parts[6]);

        ScoringType scoringType = ScoringType.valueOf(parts[7]);
        stage.setScoringType(scoringType);
        stage.setMatch(match);

        stages.add(stage);
        stageMap.put(stageSequence, stage);
    }

    private void parseStageScore(String line) {
        String[] parts = line.split(",");

        StageScore stageScore = new StageScore();

        int stageSequence = Integer.parseInt(parts[1]);
        int competitorSequence = Integer.parseInt(parts[2]);

        Competitor competitor = competitorMap.get(competitorSequence);
        Stage stage = stageMap.get(stageSequence);

        stageScore.setCompetitor(competitor);
        stageScore.setStage(stage);
        stageScore.setDQ(parts[3].equals("Yes"));
        stageScore.setACount(Integer.parseInt(parts[5]));
        stageScore.setCCount(Integer.parseInt(parts[7]));
        stageScore.setDCount(Integer.parseInt(parts[8]));
        stageScore.setMCount(Integer.parseInt(parts[9]));
        stageScore.setProcedural(Integer.parseInt(parts[10]));

        stageScores.add(stageScore);
    }
}
