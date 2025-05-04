package Assignment8;
public class GeolocationApp {

    public static void main(String[] args) {
        ConsoleUserInterface ui = new ConsoleUserInterface();
        GeoService geoService = new GoogleGeoService();

        String placeName = ui.getUserInput();
        Location location = geoService.getCoordinates(placeName);

        if (location != null) {
            ui.displayLocation(location);
        } else {
            ui.showError("Location not found.");
        }
    }
}
