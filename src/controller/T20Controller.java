package controller;

import dao.PlayerDao;
import dao.PlayerStatsDao;
import dao.ScoreboardDao;
import dao.TeamDao;
import model.Player;
import model.PlayerStats;
import model.Scoreboard;
import model.Team;
import enums.TossChoice;
import services.InningService;
import format.CricketFormat;
import services.TossService;

import java.util.List;
import java.util.Scanner;

public class T20Controller implements CricketFormat {
   private static final int OVERS = 20;
   private TeamDao teamDao;
   private ScoreboardDao scoreboardDao;
   private PlayerStatsDao playerStatsDao;
   private PlayerDao playerDao;
   Team team1;
   Team team2;
   Team bowlingTeam;
   Team battingTeam;

   public T20Controller() {
      teamDao = new TeamDao();
      this.team1 = teamDao.findByName("India");
      this.team2 = teamDao.findByName("Australia");
      scoreboardDao = new ScoreboardDao();
      playerStatsDao = new PlayerStatsDao();
      playerDao = new PlayerDao();
   }

   public void matchStarts() {
      toss();
      InningService firstInning = new InningService(battingTeam, bowlingTeam);
      firstInning.inningsStart(1000, OVERS);
      System.out.println("\nFirst Inning Ends\n");
      InningService secondInning = new InningService(bowlingTeam, battingTeam);
      secondInning.inningsStart(firstInning.getTotalRuns(), OVERS);
      System.out.println("\nSecond Innings Ends\n");
      String scoreboardId = savingResultInDatabase(firstInning, secondInning);
      List playerStatsFirstInnings = firstInning.insertBattingPlayerStats(scoreboardId);
      List playerStatsSecondInnings = firstInning.insertBowlingPlayerStats(scoreboardId);
      secondInning.updateBattingPlayerStats(scoreboardId);
      secondInning.updateBowlingPlayerStats(scoreboardId);
      updateScoreboard(scoreboardId, playerStatsFirstInnings, playerStatsSecondInnings);
      results(scoreboardId);
   }

   public String savingResultInDatabase(InningService firstInning, InningService secondInning) {
      ScoreboardDao scoreboardDao = new ScoreboardDao();
      return scoreboardDao.saveInningsData(firstInning, secondInning);
   }

   public void updateScoreboard(String scoreboardId, List playerStatsFirstInning, List playerStatsSecondInning) {
      ScoreboardDao scoreboardDao = new ScoreboardDao();
      scoreboardDao.updateInningsData(playerStatsFirstInning, playerStatsSecondInning, scoreboardId);
   }

   public void toss() {
      System.out.println("Choose 1 for \"TAILS\" or 0 for \"HEADS\" for toss as a skipper of TEAM1 :");
      Scanner scanner = new Scanner(System.in);
      int choice = scanner.nextInt(); //0 -> HEADS, 1->TAILS
      boolean tossResult = (choice == 0) ? (TossService.toss(TossChoice.HEADS)) : (TossService.toss(TossChoice.TAILS));
      //TODO assuming bowling pitch
      bowlingTeam = (tossResult == true) ? (team1) : (team2);
      battingTeam = (tossResult == false) ? (team1) : (team2);
      String result = (tossResult == true) ? ("team1 won the toss and choose to bowl first") : ("team2 won the toss and choose to bowl first");
      System.out.println(result);
   }

   public void results(String scoreboardId) {
      Scoreboard scoreboard = scoreboardDao.getScoreboardById(scoreboardId);
      if (scoreboard.getFirstInningTotalRuns() > scoreboard.getSecondInningTotalRuns())
         System.out.println(scoreboard.getFirstInningTeamName() + " Won the match!!!!");
      else if (scoreboard.getFirstInningTotalRuns() < scoreboard.getSecondInningTotalRuns())
         System.out.println(scoreboard.getSecondInningTeamName() + " Won the match!!!!");
      else System.out.println("Match Draws");
      printingAllPlayersStats(scoreboardId);
   }

   public void printingAllPlayersStats(String scoreboardId) {
      Scoreboard scoreboard = scoreboardDao.getScoreboardById(scoreboardId);
      List<String> playerStatsListFirstInning = scoreboard.getPlayerStatsFirstInning();
      List<String> playerStatsListSecondInning = scoreboard.getPlayerStatsSecondInning();
      System.out.printf("--------------------------------%n");
      System.out.printf(" Scoreboard ->  %n");
      System.out.printf("--------------------------------%n");
      System.out.printf("--------------------------------" + scoreboard.getFirstInningTeamName() + "%n");
      System.out.printf("| %-6s | %-4s | %4s  | %4s  | %4s | %4s |%n", "PlayerName", "runsScored", "ballsPlayed", "runsGiven", "ballsThrown", "wicketsTaken");

      for (int i = 0; i < 11; i++) {
         PlayerStats playerStats = playerStatsDao.getPlayerStatsById(playerStatsListFirstInning.get(i));
         Player player = playerDao.getPlayerById(playerStats.getPlayerId());
         System.out.printf("| %-6s | %-4s | %4s  | %4s  | %4s | %4s |%n", player.getName(), playerStats.getRunsScored(),
                 playerStats.getBallsPlayed(), playerStats.getRunsGiven(), playerStats.getBallsThrown(), playerStats.getWicketsTaken());
      }
      System.out.printf("--------------------------------%n");
      System.out.printf("--------------------------------" + scoreboard.getSecondInningTeamName() + "%n");
      for (int i = 0; i < 11; i++) {
         PlayerStats playerStats = playerStatsDao.getPlayerStatsById(playerStatsListSecondInning.get(i));
         Player player = playerDao.getPlayerById(playerStats.getPlayerId());
         System.out.printf("| %-6s | %-4s | %4s  | %4s  | %4s | %4s |%n", player.getName(), playerStats.getRunsScored(),
                 playerStats.getBallsPlayed(), playerStats.getRunsGiven(), playerStats.getBallsThrown(), playerStats.getWicketsTaken());
      }
   }
}
