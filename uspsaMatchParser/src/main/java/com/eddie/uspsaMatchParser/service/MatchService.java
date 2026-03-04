package com.eddie.uspsaMatchParser.service;

import com.eddie.uspsaMatchParser.models.ParsedMatchResult;
import com.eddie.uspsaMatchParser.repository.CompetitorRepo;
import com.eddie.uspsaMatchParser.repository.MatchRepo;
import com.eddie.uspsaMatchParser.repository.StageRepo;
import com.eddie.uspsaMatchParser.repository.StageScoreRepo;
import com.eddie.uspsaMatchParser.util.MatchParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final CompetitorRepo competitorRepo;
    private final MatchRepo matchRepo;
    private final StageRepo stageRepo;
    private final StageScoreRepo stageScoreRepo;

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

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Failed", HttpStatus.FORBIDDEN);
    }
}
