import java.util.ArrayList;

public class Scoreboard {
    private int total_runs;
    private int wickets_loss;
    private String team_name;
    private Team team;
    public Scoreboard(Team team){
//        this.total_runs=0;
//        this.wickets_loss=0;
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

    public void get_scores(){
        System.out.println(team.getTeam_name()+" scored -> "+team.getTotal_runs()+" runs in loss of "+team.getWickets_loss()+" wickets");
        System.out.println();
        for(int i=0;i<11;i++){
            System.out.println(team.getDetails().get(i).getname()+" scores -> "+ team.getDetails().get(i).getRuns_scored()+" in "+team.getDetails().get(i).getBalls_played());
        }
        System.out.println("\n");
    }
}
