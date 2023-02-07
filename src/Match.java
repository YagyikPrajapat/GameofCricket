import java.util.Scanner;

public class Match {
    public void play() {
        Scanner scanner = new Scanner(System.in);
        String formatC = scanner.next();
        System.out.println(formatC);
        if (formatC.equals("t20")) {
            CricketFormat match = new T20();
            match.matchStarts();
        } else if (formatC.equals("odi")) {
            CricketFormat match = new T20();
            match.matchStarts();
        } else {
            System.out.println("Not Yet Done!!!");
        }
    }
}
