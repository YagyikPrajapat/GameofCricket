import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Team india=new Team("india");
        Team pakistan=new Team("pakistan");

        //Select 1 or 0 in toss
        System.out.println("Choose 1 or 0 for toss as a skipper of Indian Team:");
        Toss toss=new Toss("india","pakistan");
        String toss_result=toss.toss();
        //Assuming Bowling Pitch
        System.out.println(toss_result+ " won the toss and choose to bowl first");


        MatchController match=new MatchController();

        //taking over as input
        match.overs();
        if(toss_result=="pakistan") {
            match.Match(india);
            System.out.println("first innings ends\n");
            match.Match(pakistan);
            System.out.println("second innings ends\n");
        }
        else{
            match.Match(pakistan);
            System.out.println("first innings ends\n");
            match.Match(india);
            System.out.println("second innings ends\n");
        }

        Scoreboard india_scoreboard= new Scoreboard(india);
        Scoreboard pakistan_scoreboard= new Scoreboard(pakistan);


         india_scoreboard.get_scores();
         pakistan_scoreboard.get_scores();
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