package com.eddie.uspsaMatchParser.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stageId;

    @ManyToOne
    @JoinColumn(name = "matchId")
    private Match match;

    private int stageNo;
    private int roundCount;
    private int stagePts;
    private boolean isClassifier;
    private String classifierName;
    private String stageName;

    @Enumerated(EnumType.STRING)
    private ScoringType scoringType;

    @Override
    public String toString() {
        return "Stage{" +
                "stageId=" + stageId +
                ", matchId=" + match +
                ", stageNo=" + stageNo +
                ", roundCount=" + roundCount +
                ", stagePts=" + stagePts +
                ", isClassifier=" + isClassifier +
                ", classifierName='" + classifierName + '\'' +
                ", stageName='" + stageName + '\'' +
                ", scoringType=" + scoringType +
                '}';
    }
}
