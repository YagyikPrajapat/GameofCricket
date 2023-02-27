import format.CricketFormat;
import controller.MatchController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please write Format you want to play");
        Scanner scanner = new Scanner(System.in);
        String matchFormat = scanner.next();
        MatchController matchController = new MatchController();
        CricketFormat match = matchController.matchFormatChoice(matchFormat);
        match.matchStarts();
    }
}