import java.util.ArrayList;

public class Team {
    private String team_name;
    private int total_runs;
    private int wickets_loss;
    ArrayList<Player> team;
    String [] players_india={"i1","i2","i3","i4","i5","i6","i7","i8","i9","i10","i11"};
    String [] players_pakistan={"p1","p2","p3","p4","p5","p6","p7","p8","p9","p10","p11"};
    public Team(String team_name){
        this.team=new ArrayList<>();
        this.team_name=team_name;
        this.total_runs=0;
        this.wickets_loss=0;
    }

    public int getTotal_runs() {
        return total_runs;
    }

    public void setTotal_runs(int total_runs) {
        this.total_runs = total_runs;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setWickets_loss(int wickets_loss) {
        this.wickets_loss = wickets_loss;
    }

    public int getWickets_loss() {
        return wickets_loss;
    }

    public ArrayList<Player> getDetails(){
        for(int i=0;i<11;i++){
            if(team_name.equals("india")){
                Player p=new Player(players_india[i]);
                team.add(p);
            }
            else if(team_name.equals("pakistan")){
                Player p=new Player(players_pakistan[i]);
                team.add(p);
            }
        }
        return team;
    }

}
