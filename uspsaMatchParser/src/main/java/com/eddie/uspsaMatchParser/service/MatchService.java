package com.eddie.uspsaMatchParser.service;

import com.eddie.uspsaMatchParser.models.Competitor;
import com.eddie.uspsaMatchParser.models.Match;
import com.eddie.uspsaMatchParser.models.ParsedMatchResult;
import com.eddie.uspsaMatchParser.repository.*;
import com.eddie.uspsaMatchParser.util.MatchParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final CompetitorRepo competitorRepo;
    private final MatchRepo matchRepo;
    private final StageRepo stageRepo;
    private final StageScoreRepo stageScoreRepo;
    private final MatchEntryRepo matchEntryRepo;

    @Autowired
    MatchParser matchParser;

    public ResponseEntity<String> parseMatch(MultipartFile file) {
        try {
            String content = new String(file.getBytes());
            ParsedMatchResult result = matchParser.parseMatchData(content);

            // Check if match data is a duplicate
            if (matchRepo.existsByMatchNameAndMatchDate(result.getMatch().getMatchName(),
                    result.getMatch().getMatchDate())) {
                return new ResponseEntity<>("Match already exists", HttpStatus.CONFLICT);
            }

            competitorRepo.saveAll(result.getCompetitors());
            matchRepo.save(result.getMatch());
            stageRepo.saveAll(result.getStages());
            stageScoreRepo.saveAll(result.getStageScores());
            matchEntryRepo.saveAll(result.getMatchEntries());


            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Failed", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<List<Match>> getMatches() {
        try {
            return new ResponseEntity<>(matchRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Competitor>> getCompetitors() {
        try {
            return new ResponseEntity<>(competitorRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Competitor> getCompetitorById(int competitorId) {
        try {
            return new ResponseEntity<>(competitorRepo.findById(competitorId).get(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new Competitor(), HttpStatus.NOT_FOUND);
        }
    }
}
