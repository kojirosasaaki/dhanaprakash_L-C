package Assignment3;

import java.util.Scanner;

public class RoundRobinTournament {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        TournamentCalculator calculator = new TournamentCalculator();
        OutputPrinter outputPrinter = new OutputPrinter();

        int numberOfTeams = inputHandler.getNumberOfTeams();

        if (numberOfTeams < 2) {
            outputPrinter.printInvalidTeamMessage();
        } else {
            int totalMatches = calculator.calculateRoundRobinMatches(numberOfTeams);
            outputPrinter.printMatchCount(totalMatches);
        }

        inputHandler.closeScanner();
    }
}


class InputHandler {
    private Scanner scanner = new Scanner(System.in);

    public int getNumberOfTeams() {
        System.out.print("Enter the no of teams: ");
        return scanner.nextInt();
    }

    public void closeScanner() {
        scanner.close();
    }
}


class TournamentCalculator {
    public int calculateRoundRobinMatches(int numberOfTeams) {
        if (numberOfTeams < 2) {
            return 0;
        }
        return (numberOfTeams * (numberOfTeams - 1)) / 2;
    }
}


class OutputPrinter {
    public void printInvalidTeamMessage() {
        System.out.println("There must be at least two teams to play a Round Robin tournament.");
    }

    public void printMatchCount(int totalMatches) {
        System.out.println("Total no of matches to be played in Round Robin method: " + totalMatches);
    }
}
