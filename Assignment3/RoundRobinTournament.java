package Assignment3;

import java.util.Scanner;

public class RoundRobinTournament {

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the no of teams: ");
        int numberOfTeams = scanner.nextInt();
        
        if (numberOfTeams < 2) {
            System.out.println("There must be at least two teams to play a Round Robin tournament.");
        } 
        else {
            int totalMatches = calculateRoundRobinMatches(numberOfTeams);
            System.out.println("Total no of matches to be played in Round Robin method: " + totalMatches);
        }
        
        scanner.close();
    }
    
    public static int calculateRoundRobinMatches(int numberOfTeams) {
        if (numberOfTeams < 2) {
            return 0;  
        }
        return (numberOfTeams * (numberOfTeams - 1)) / 2;  
    }
}
