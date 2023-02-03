import java.util.Scanner;

public class Toss {
    private String india;
    private String pakistan;
    public Toss(String india,String pakistan){
        this.india=india;
        this.pakistan=pakistan;
    }

    public String toss(){
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
