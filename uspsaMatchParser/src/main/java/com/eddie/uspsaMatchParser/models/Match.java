package com.eddie.uspsaMatchParser.models;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"match_name", "match_date"})
})
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int matchId;
  private String matchName;
  private LocalDate matchDate;
  private String clubName;

  @Enumerated(EnumType.STRING)
  private MatchType matchType;
  private String matchLevel;

  @Override
  public String toString() {
    return "Match Details: "+ "\n" +
            "matchId = " + matchId + "\n" +
            "matchName = " + matchName + "\n" +
            "matchDate = " + matchDate + "\n" +
            "clubName = "  + clubName + "\n" +
            "matchType = " + matchType + "\n" +
            "matchLevel = " + matchLevel;
  }
}
