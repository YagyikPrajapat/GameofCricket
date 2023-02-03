import java.util.ArrayList;
public class Player {
    private String name;
    private int runs_scored;
    private int balls_played;
    private int wickets_taken;
    private int balls_thrown;

    public Player(String name){
        this.name=name;
//        this.runs_scored=0;
//        this.balls_thrown=0;
//        this.balls_played=0;
//        this.wickets_taken=0;
    }

    public int getRuns_scored() {
        return runs_scored;
    }

    public int getBalls_played() {
        return balls_played;
    }

    public int getWickets_taken() {
        return wickets_taken;
    }

    public int getBalls_thrown() {
        return balls_thrown;
    }

    public void setRuns_scored(int runs_scored) {
        this.runs_scored = runs_scored;
    }

    public void setBalls_played(int balls_played) {
        this.balls_played = balls_played;
    }

    public void setWickets_taken(int wickets_taken) {
        this.wickets_taken = wickets_taken;
    }

    public void setBalls_thrown(int balls_thrown) {
        this.balls_thrown = balls_thrown;
    }

    public String getname(){
       return name;
    }

    public void details(int player_number){

    }
}
