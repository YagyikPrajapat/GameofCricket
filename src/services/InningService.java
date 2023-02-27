package services;

import dao.PlayerStatsDao;
import dao.TeamDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Team;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class InningService {
   private Team battingTeam;
   private Team bowlingTeam;
   private TeamDao teamDao;
   private List<Integer> playerRuns;
   private List<Integer> ballsPlayed;
   private List<Integer> runsGiven;
   private List<Integer> ballsThrown;
   private List<Integer> wicketsTaken;

   public InningService(Team battingTeam, Team bowlingTeam) {
      this.battingTeam = battingTeam;
      this.bowlingTeam = bowlingTeam;
      teamDao = new TeamDao();
      this.ballsPlayed = new ArrayList<>(Collections.nCopies(11, 0));
      this.playerRuns = new ArrayList<>(Collections.nCopies(11, 0));
      this.runsGiven = new ArrayList<>(Collections.nCopies(11, 0));
      this.ballsThrown = new ArrayList<>(Collections.nCopies(11, 0));
      this.wicketsTaken = new ArrayList<>(Collections.nCopies(11, 0));
   }

   private int strike = 0, non_strike = 1, next_batsman = 2, bowler = 6;
   private int totalWickets = 0, totalRuns = 0;

   public void inningsStart(int target) {
      for (int i = 0; i < 2; i++) {
         int ball = 0, flag = 0;
         for (int j = 0; j < 6; j++) {
            if (totalWickets >= 10 || totalRuns > target) {
               flag = 1;
               break;
            }
            ball = (int) (Math.random() * 8);
            updateBallsPlayedAndThrown(strike, bowler);  //updating model.Scoreboard
            ballDecision(ball);
         }
         if (flag == 1) break;
         if (ball % 2 != 0) {
            swapPlayers();
         }
         bowler++;
         if (bowler > 10) bowler = 6;
         System.out.println(" ** over ends **");
      }
   }

   private void ballDecision(int ball) {
      if (ball == 7) {
         System.out.print("Wicket ");
         totalWickets++;
         wicketsTaken.set(bowler, wicketsTaken.get(bowler) + 1); //updating wickets taken
         strike = next_batsman;
         next_batsman++;
      } else {
         System.out.print(ball + " ");
         totalRuns += ball;
         updateRunsScoredAndRunsGiven(strike, bowler, ball);
         if (ball % 2 != 0) {
            swapPlayers();
         }
      }
   }

   private void swapPlayers() {
      int temp = strike;
      strike = non_strike;
      non_strike = temp;
   }
   private void updateRunsScoredAndRunsGiven(int strike, int bowler, int ball) {
      playerRuns.set(strike, playerRuns.get(strike) + ball);
      runsGiven.set(bowler, runsGiven.get(bowler) + ball);
   }

   private void updateBallsPlayedAndThrown(int strike, int bowler) {
      ballsPlayed.set(strike, ballsPlayed.get(strike) + 1);
      ballsThrown.set(bowler, ballsThrown.get(bowler) + 1);
   }

   public List insertBowlingPlayerStats(String scoreboardId) {
      PlayerStatsDao playerStatsDao = new PlayerStatsDao();
      List<String>  bowling = new ArrayList<>();
      for (int i = 0; i < 11; i++) {
         String playerId1 = teamDao.getBatsmanId(bowlingTeam.get_id(), i);
         String id2 = playerStatsDao.insertPlayersStats(playerId1, playerRuns.get(i), ballsPlayed.get(i),
                 ballsThrown.get(i), runsGiven.get(i), wicketsTaken.get(i), scoreboardId);
         bowling.add(id2);
      }
      return bowling;
   }
   public List insertBattingPlayerStats(String scoreboardId) {
      PlayerStatsDao playerStatsDao = new PlayerStatsDao();
      List<String>  batter = new ArrayList<>();
      for (int i = 0; i < 11; i++) {
         String playerId = teamDao.getBatsmanId(battingTeam.get_id(), i);
         String id1 = playerStatsDao.insertPlayersStats(playerId, playerRuns.get(i), ballsPlayed.get(i),
                 ballsThrown.get(i), runsGiven.get(i), wicketsTaken.get(i), scoreboardId);
         batter.add(id1);
      }
      return batter;
   }

   public void updateBattingPlayerStats(String scoreboardId) {
      PlayerStatsDao playerStatsDao = new PlayerStatsDao();
      List<String> st = new ArrayList<>();
      for (int i = 0; i < 11; i++) {
         String playerId = teamDao.getBatsmanId(battingTeam.get_id(), i);
         playerStatsDao.updatePlayersBattingStats(playerId, playerRuns.get(i), ballsPlayed.get(i), scoreboardId);
      }
   }
   public void updateBowlingPlayerStats(String scoreboardId) {
      PlayerStatsDao playerStatsDao = new PlayerStatsDao();
      List<String> st = new ArrayList<>();
      for (int i = 0; i < 11; i++) {
         String playerId1 = teamDao.getBatsmanId(bowlingTeam.get_id(), i);
         playerStatsDao.updatePlayersBowlingStats(playerId1, ballsThrown.get(i),
                 runsGiven.get(i), wicketsTaken.get(i), scoreboardId);
      }
   }
}
