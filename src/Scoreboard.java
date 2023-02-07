import java.util.ArrayList;
import java.util.Map;

public class Scoreboard {

    private int total_runs;
    private int wickets_loss;
    private String team_name;
    protected Team team;
    public Scoreboard(Team team){
        this.team=team;
    }

    public int getTotal_runs() {
        return total_runs;
    }

    public int getWickets_loss() {
        return wickets_loss;
    }

    public void setWickets_loss(int wickets_loss) {
        this.wickets_loss = wickets_loss;
    }

    public void setTotal_runs(int total_runs) {
        this.total_runs = total_runs;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

}
