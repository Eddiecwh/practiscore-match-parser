package com.eddie.uspsaMatchParser.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MatchEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchEntryId;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "competitor_id")
    private Competitor competitor;

    private String division;
    private String classification;
    private double matchPoints;
    private int placeOverall;
    private int placeDivision;
}
