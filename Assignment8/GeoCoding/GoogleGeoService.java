package Assignment8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class GoogleGeoService implements GeoService {

    private static final String API_KEY = "AIzaSyDane-f3NTB5qK0SW5iUGR0AGSc4dG_9nA";

    @Override
    public Location getCoordinates(String placeName) {
        try {
            String encodedPlace = URLEncoder.encode(placeName, "UTF-8");
            String apiUrl = buildApiUrl(encodedPlace);
            String jsonResponse = fetchData(apiUrl);
            return parseCoordinates(jsonResponse);
        } catch (Exception e) {
            return null;
        }
    }

    private String buildApiUrl(String placeName) {
        return "https://maps.googleapis.com/maps/api/geocode/json?address=" + placeName + "&key=" + API_KEY;
    }

    private String fetchData(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    private Location parseCoordinates(String json) {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.getString("status").equals("OK")) {
            JSONObject location = jsonObject.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");

            double lat = location.getDouble("lat");
            double lng = location.getDouble("lng");
            return new Location(lat, lng);
        }
        return null;
    }
}

