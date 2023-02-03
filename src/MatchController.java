import java.util.ArrayList;
import java.util.Scanner;

public class MatchController {
    private int overs;
    ArrayList<Integer> overs_run;
    Team india=new Team("india");
    Team pakistan=new Team("pakistan");
//    ArrayList<Player> india_team=india.getDetails();
//    ArrayList<Player> pakistan_team=pakistan.getDetails();
//    ArrayList<Player> batting_team;
//    ArrayList<Player> bowling_team;
    public MatchController(){
        this.overs_run=new ArrayList<>();
    }

    public void overs(){
        System.out.println("Mention the number of overs");
        Scanner scanner=new Scanner(System.in);
        overs=scanner.nextInt();
    }

    public int getOvers() {
        return overs;
    }

    public void Match(Team team){
        int total_runs=0,total_wickets=0;
        int strike=0,non_strike=1,next_batsman=2;
        int bowler=7;
        for(int i=0;i<overs;i++) {
            int ball=0,run=0;
            for (int j = 0; j < 6; j++) {
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

//        for(int i=0;i<11;i++){
//            System.out.print(batting_team.get(i).getname()+" -> ");
//            System.out.print(batting_team.get(i).getRuns_scored());
//            System.out.print("/");
//            System.out.print(batting_team.get(i).getBalls_played());
//            System.out.print(" , ");
//        }
      //  System.out.println(total_runs);
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

    }

}
