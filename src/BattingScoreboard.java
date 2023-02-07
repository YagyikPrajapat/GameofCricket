import java.util.HashMap;
import java.util.Map;

public class BattingScoreboard extends Scoreboard{
    private int runsScored;
    private int ballsPlayed;
    private Map<String,Integer> playerRuns=new HashMap<String,Integer>();
    private Map<String,Integer> playerBallsPlayed=new HashMap<String,Integer>();
    public BattingScoreboard(Team team) {
        super(team);
    }

    public int getRunsScored(String id) {
        return playerRuns.get(id);
    }

    public void setRunsScored(int runsScored, String id) {
        if (!playerRuns.containsKey(id)){
            playerRuns.put(id, runsScored);
        }
        else {
            playerRuns.put(id, playerRuns.get(id) + runsScored);
        }
    }

    public int getBallsPlayed(String id) {
        return playerBallsPlayed.get(id);
    }

    public void setBallsPlayed(int ballsPlayed, String id) {
        if (!playerBallsPlayed.containsKey(id)){
            playerBallsPlayed.put(id, ballsPlayed);
        }
        else {
            playerBallsPlayed.put(id, playerBallsPlayed.get(id) + ballsPlayed);
        }
    }

    public void showStats(){
        System.out.println();
        System.out.println("Runs made by "+team.getTeam_name());
        for(int i=0;i<11;i++){
            if(playerRuns.containsKey(team.getDetails().get(i).getName()+i)){
                System.out.println(team.getDetails().get(i).getName()+" scores "+
                        playerRuns.get(team.getDetails().get(i).getName()+i)+" runs in "+
                        playerBallsPlayed.get(team.getDetails().get(i).getName()+i) + " balls "
                        );
            }
        }
        System.out.println();
    }
}
