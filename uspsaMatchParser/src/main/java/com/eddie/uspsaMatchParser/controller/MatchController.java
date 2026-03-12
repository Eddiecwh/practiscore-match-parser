package com.eddie.uspsaMatchParser.controller;

import com.eddie.uspsaMatchParser.models.Competitor;
import com.eddie.uspsaMatchParser.models.Match;
import com.eddie.uspsaMatchParser.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("/matches")
    ResponseEntity<List<Match>> getMatches() {
        return matchService.getMatches();
    }

    @GetMapping("/competitors")
    ResponseEntity<List<Competitor>> getCompetitors() {
        return matchService.getCompetitors();
    }

    @GetMapping("/competitor/{competitorId}")
    ResponseEntity<Competitor> getCompetitorById(@PathVariable int competitorId) {
        return matchService.getCompetitorById(competitorId);
    }

    @PostMapping("/upload")
    ResponseEntity<String> uploadMatch(@RequestParam("file") MultipartFile file) {
        return matchService.parseMatch(file);
    }
}
