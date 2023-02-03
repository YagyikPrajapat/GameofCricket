import java.util.ArrayList;
import java.util.Scanner;

public class MatchController {
    private int overs;
    ArrayList<Integer> overs_run;
    Team india;
    Team pakistan;
    public MatchController(){
        this.overs_run=new ArrayList<>();
        this.india=new Team("india");
        this.pakistan=new Team("pakistan");
    }

    public void matchStarts(){
        overs();
        Team toss_result=Toss();
        if(toss_result.getTeam_name().equals("pakistan")) {
            Match(india);
            System.out.println("first innings ends\n");
            Match(pakistan);
            System.out.println("second innings ends\n");
        }
        else{
            Match(pakistan);
            System.out.println("first innings ends\n");
            Match(india);
            System.out.println("second innings ends\n");
        }
        Scoreboard india_scoreboard= new Scoreboard(india);
        Scoreboard pakistan_scoreboard= new Scoreboard(pakistan);


        india_scoreboard.get_scores();
        pakistan_scoreboard.get_scores();

        results();
    }

    public void overs(){
        System.out.println("Mention the number of overs");
        Scanner scanner=new Scanner(System.in);
        overs=scanner.nextInt();
    }

    public int getOvers() {
        return overs;
    }

    public Team Toss(){
        System.out.println("Choose 1 or 0 for toss as a skipper of Indian Team:");
        Toss toss=new Toss(india,pakistan);
        Team toss_result=toss.toss();
        System.out.println(toss_result.getTeam_name());
        return toss_result;
    }

    public void Match(Team team){
        int total_runs=0,total_wickets=0;
        int strike=0,non_strike=1,next_batsman=2;
        int bowler=7;
        for(int i=0;i<overs;i++) {
            int ball=0,run=0,flag=0;
            for (int j = 0; j < 6; j++) {
                if(total_wickets>=10) {flag=1;break;}
                ball = (int) (Math.random() * 8);
                team.getDetails().get(strike).setBalls_played(team.getDetails().get(strike).getBalls_played()+1);
                if (ball == 7) {
                    System.out.print("Wicket ");
                    total_wickets++;
                    //bowling_team.get(bowler).setWickets_taken(bowling_team.get(bowler).getWickets_taken());
                    strike=next_batsman;
                    next_batsman++;
                } else {
                    System.out.print(ball);
                    System.out.print(" ");
                    total_runs += ball;
                    run+=ball;
                    team.getDetails().get(strike).setRuns_scored(team.getDetails().get(strike).getRuns_scored()+ball);
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
            //bowling_team.get(bowler).setBalls_thrown(bowling_team.get(bowler).getBalls_thrown()+6);
            System.out.println(" ** over ends **");
            overs_run.add(run);
        }
        team.setTotal_runs(total_runs);
        team.setWickets_loss(total_wickets);
    }

    public void setOvers_run(ArrayList<Integer> overs_run) {
        this.overs_run = overs_run;
    }

    public ArrayList<Integer> getOvers_run() {
        return overs_run;
    }

    public void results(){
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
