package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerStats {
   private String _id;
   private String playerId;
   private String scoreboardId;
   private int runsScored;
   private int ballsPlayed;
   private int runsGiven;
   private int ballsThrown;
   private int wicketsTaken;

}
