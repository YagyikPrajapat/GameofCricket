package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Scoreboard {
    private String _id;
    private int firstInningTotalRuns;
    private int  firstInningWicketsLoss;
    private int secondInningTotalRuns;
    private int  secondInningWicketsLoss;
    private String firstInningTeamName;
    private String secondInningTeamName;
    private List<String> playerStatsFirstInning;
    private List<String> playerStatsSecondInning;

}
