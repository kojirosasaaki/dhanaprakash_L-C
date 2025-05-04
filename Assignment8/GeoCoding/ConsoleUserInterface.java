package Assignment8;

import java.util.Scanner;

public class ConsoleUserInterface {
    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        System.out.print("Enter place name: ");
        return scanner.nextLine();
    }

    public void displayLocation(Location location) {
        System.out.println("Latitude: " + location.getLatitude());
        System.out.println("Longitude: " + location.getLongitude());
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}

