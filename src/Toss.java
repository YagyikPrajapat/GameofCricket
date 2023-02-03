import java.util.Scanner;

public class Toss {
    private Team india;
    private Team pakistan;
    public Toss(Team india,Team pakistan){
        this.india=india;
        this.pakistan=pakistan;
    }

    public Team toss(){
        Scanner scanner=new Scanner(System.in);
        int choose= scanner.nextInt();
        int toss_result=(int) (Math.random()*2);
        if(toss_result==choose){
            return india;
        }
        else{
            return pakistan;
        }
    }
}
