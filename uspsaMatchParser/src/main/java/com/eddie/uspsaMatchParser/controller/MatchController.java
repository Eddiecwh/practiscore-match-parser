package com.eddie.uspsaMatchParser.controller;

import com.eddie.uspsaMatchParser.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping("/upload")
    ResponseEntity<String> uploadMatch(@RequestParam("file") MultipartFile file) {
        return matchService.parseMatch(file);
    }
}
