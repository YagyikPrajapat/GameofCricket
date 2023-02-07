import java.util.HashMap;
import java.util.Map;

public class BowlingScoreboard extends Scoreboard{
    private int ballsThrown;
    private int runsGiven;
    private Map<String,Integer> playerRunsGiven = new HashMap<String,Integer>();
    private Map<String,Integer> playerBallsThrown = new HashMap<String,Integer>();
    private Map<String,Integer> playerWicketsTaken = new HashMap<String,Integer>();


    public BowlingScoreboard(Team team) {
        super(team);
    }

    public int getBallsThrown(String id) {
        return playerBallsThrown.get(id);
    }

    public void setBallsThrown(int ballsThrown, String id) {
        if(!playerBallsThrown.containsKey(id)){
            playerBallsThrown.put(id,1);
        }
        else{
            playerBallsThrown.put(id,playerBallsThrown.get(id)+1);
        }
    }

    public void setRunsGiven(int runsGiven, String id) {
        if(!playerRunsGiven.containsKey(id)){
            playerRunsGiven.put(id,runsGiven);
        }
        else{
            playerRunsGiven.put(id,playerRunsGiven.get(id)+1);
        }
    }

    public int getRunsGiven(String id) {
        return playerRunsGiven.get(id);
    }

    public int getWicketsTaken(String id) {
        return playerWicketsTaken.get(id);
    }

    public void setWicketsTaken(int wicketsTaken, String id) {
        if(!playerWicketsTaken.containsKey(id)){
            playerWicketsTaken.put(id, 1);
        }
        else{
            playerWicketsTaken.put(id, playerWicketsTaken.get(id)+1);
        }
    }

    public void showStats(){
        System.out.println();
        System.out.println("Wickets taken by "+team.getTeam_name());
        for(int i=0;i<11;i++){
            if(playerWicketsTaken.containsKey(team.getDetails().get(i).getName()+i)){
                System.out.println(team.getDetails().get(i).getName()+" takes "+
                        playerWicketsTaken.get(team.getDetails().get(i).getName()+i) + " wickets giving " +
                        playerRunsGiven.get(team.getDetails().get(i).getName()+i) + " runs in "+
                        playerRunsGiven.get(team.getDetails().get(i).getName()+i) + " balls "
                        );
            }
        }
        System.out.println();
    }
}
