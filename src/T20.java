import java.util.ArrayList;
import java.util.Scanner;

public class T20 implements CricketFormat {
    private int overs;
    ArrayList<Integer> overs_run;
    Team india;
    Team pakistan;
    BattingScoreboard indiaBattingScoreboard;
    BattingScoreboard pakistanBattingScoreboard;
    BowlingScoreboard indiaBowlingScoreboard;
    BowlingScoreboard pakistanBowlingScoreboard;
    public T20(){
        this.overs_run = new ArrayList<>();
        this.india = new Team("india");
        this.pakistan = new Team("pakistan");
        this.indiaBattingScoreboard=new BattingScoreboard(india);
        this.pakistanBattingScoreboard=new BattingScoreboard(pakistan);
        this.indiaBowlingScoreboard=new BowlingScoreboard(india);
        this.pakistanBowlingScoreboard=new BowlingScoreboard(pakistan);
    }

    public void matchStarts(){
        overs();
        Team toss_result = toss();
        if(toss_result.getTeam_name().equals("pakistan")) {
            match(india,pakistan);
            System.out.println("\n\nfirst innings ends\n");
            match(pakistan,india);
            System.out.println("\n\nsecond innings ends\n");
        }
        else{
            match(pakistan,india);
            System.out.println("\n\nfirst innings ends\n");
            match(india,pakistan);
            System.out.println("\n\nsecond innings ends\n");
        }
        indiaBattingScoreboard.showStats();
        indiaBowlingScoreboard.showStats();
        pakistanBattingScoreboard.showStats();
        pakistanBowlingScoreboard.showStats();

        results();
    }

    public void overs(){
        System.out.println("Mention the number of overs");
        Scanner scanner = new Scanner(System.in);
        overs = scanner.nextInt();
    }

    public int getOvers() {
        return overs;
    }

    public Team toss(){
        System.out.println("Choose 1 or 0 for toss as a skipper of Indian Team:");
        Toss toss = new Toss(india, pakistan);
        Team toss_result = toss.toss();
        System.out.println(toss_result.getTeam_name()+" won the toss and choose to bowl first\n" );
        return toss_result;
    }

    public void match(Team battingTeam, Team bowlingTeam){
        int total_runs=0,total_wickets=0;
        int strike=0,non_strike=1,next_batsman=2;
        int bowler=7;
        for(int i=0;i<overs;i++) {
            int ball=0,run=0,flag=0;
            for (int j = 0; j < 6; j++) {
                if(total_wickets>=10) {flag=1;break;}
                ball = (int) (Math.random() * 8);

                //updating Scoreboard
                updateBallsPlayedAndThrown(battingTeam, bowlingTeam, strike, bowler);

                if (ball == 7) {
                    System.out.print("Wicket ");
                    total_wickets++;

                    updateWicketsTaken(bowlingTeam, bowler); //updating scoreboard (bowling)

                    strike=next_batsman;
                    next_batsman++;
                } else {
                    System.out.print(ball);
                    System.out.print(" ");
                    total_runs += ball;
                    run+=ball;
                   //updating scoreboard
                    updateRunsScoredAndRunsGiven(battingTeam, bowlingTeam, strike, bowler, ball);

                    if(ball%2!=0){
                        int temp=strike;
                        strike=non_strike;
                        non_strike=temp;
                    }
                }
            }
            if(flag==1) break;
            if(ball%2!=0){
                int temp=strike;
                strike=non_strike;
                non_strike=temp;
            }
            bowler++;
            if(bowler>=11) bowler=7;
            System.out.println(" ** over ends **");
        }
        battingTeam.setTotal_runs(total_runs);
        battingTeam.setWickets_loss(total_wickets);
    }
    public void update(int a){
        a=0;
    }

    public void updateBallsPlayedAndThrown(Team battingTeam, Team bowlingTeam, int strike, int bowler){
        if (battingTeam.getTeam_name().equals("india")) {
            indiaBattingScoreboard.setBallsPlayed(1, battingTeam.getDetails().get(strike).getId());
            pakistanBowlingScoreboard.setBallsThrown(1, bowlingTeam.getDetails().get(bowler).getId());
        }
        else {
            pakistanBattingScoreboard.setBallsPlayed(1, battingTeam.getDetails().get(strike).getId());
            indiaBowlingScoreboard.setBallsThrown(1, bowlingTeam.getDetails().get(bowler).getId());
        }
    }

    public void updateWicketsTaken(Team bowlingTeam, int bowler){
        if (bowlingTeam.getTeam_name().equals("pakistan"))
            pakistanBowlingScoreboard.setWicketsTaken(1, bowlingTeam.getDetails().get(bowler).getId());
        else
            indiaBowlingScoreboard.setWicketsTaken(1, bowlingTeam.getDetails().get(bowler).getId());
    }

    public void updateRunsScoredAndRunsGiven(Team battingTeam, Team bowlingTeam, int strike, int bowler,int ball){
        if (battingTeam.getTeam_name().equals("india")){
            indiaBattingScoreboard.setRunsScored(ball, battingTeam.getDetails().get(strike).getId());
            pakistanBowlingScoreboard.setRunsGiven(ball, bowlingTeam.getDetails().get(bowler).getId());
        }
        else{
            pakistanBattingScoreboard.setRunsScored(ball, battingTeam.getDetails().get(strike).getId());
            indiaBowlingScoreboard.setRunsGiven(ball, bowlingTeam.getDetails().get(bowler).getId());
        }
    }

    public void setOvers_run(ArrayList<Integer> overs_run) {
        this.overs_run = overs_run;
    }

    public ArrayList<Integer> getOvers_run() {
        return overs_run;
    }

    public void results(){
        System.out.println("India made "+ india.getTotal_runs() + " and lost " + india.getWickets_loss() + " wickets.");
        System.out.println("\nPakistan made "+ pakistan.getTotal_runs() + " and lost " + pakistan.getWickets_loss() + " wickets.\n");
        if(india.getTotal_runs()> pakistan.getTotal_runs()){
            System.out.println("india won the match");
        }
        else if(india.getTotal_runs()<pakistan.getTotal_runs()){
            System.out.println("pakistan won the match");
        }
        else{
            System.out.println("Match Draw");
        }
    }

}
