package com.eddie.uspsaMatchParser.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StageScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stageScoreId;

    @ManyToOne
    @JoinColumn(name = "competitorId")
    private Competitor competitor;

    @ManyToOne
    @JoinColumn(name = "stageId")
    private Stage stage;

    private boolean DQ;
    private boolean DNF;
    private int aCount;
    private int cCount;
    private int dCount;
    private int mCount;
    private int nsCount;
    private int procedural;
    private int stagePoints;
    private int stagePlace;

    @Override
    public String toString() {
        return "StageScore{" +
                "stageScoreId=" + stageScoreId +
                ", competitor=" + competitor +
                ", stage=" + stage +
                ", DQ=" + DQ +
                ", DNF=" + DNF +
                ", aCount=" + aCount +
                ", cCount=" + cCount +
                ", dCount=" + dCount +
                ", mCount=" + mCount +
                ", nsCount=" + nsCount +
                ", procedural=" + procedural +
                ", stagePoints=" + stagePoints +
                ", stagePlace=" + stagePlace +
                '}';
    }
}
