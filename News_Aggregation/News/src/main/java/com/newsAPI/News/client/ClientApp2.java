package com.newsAPI.News.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsAPI.News.model.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

public class ClientApp2 {
    private static final String BASE_URL = "http://localhost:8080/api/users";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[Client 2]");
        System.out.print("Enter username: ");
        String usernameInput = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user = new User(usernameInput, email);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        URL url = new URL(BASE_URL + "/add");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        String username = "admin";
        String password = "admin123";
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeaderValue = "Basic " + encodedAuth;
        conn.setRequestProperty("Authorization", authHeaderValue);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        int responseCode = conn.getResponseCode();
        System.out.println("[Client 2] Server Response Code: " + responseCode);

        if (responseCode == 200 || responseCode == 201) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Response from server: " + response);
        } else {
            System.out.println("Failed to create user. Server says: " + conn.getResponseMessage());
        }

        conn.disconnect();
    }
}
