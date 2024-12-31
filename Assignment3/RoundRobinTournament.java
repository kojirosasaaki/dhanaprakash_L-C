import java.util.Scanner;

public class RoundRobinTournament {

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the no of teams: ");
        int teams = scanner.nextInt();
        
        if (teams < 2) {
            System.out.println("There must be at least two teams to play a Round Robin tournament.");
        } 
        else {
            int matches = calculateRoundRobinMatches(teams);
            System.out.println("Total no of matches to be played in Round Robin method: " + matches);
        }
        
        scanner.close();
    }
    
    public static int calculateRoundRobinMatches(int teams) {
        if (teams < 2) {
            return 0;  
        }
        return (teams * (teams - 1)) / 2;  
    }
}
