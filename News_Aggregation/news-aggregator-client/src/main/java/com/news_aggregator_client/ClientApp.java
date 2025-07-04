package com.news_aggregator_client;

import java.util.Scanner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ClientApp {

    private static final RestTemplate rest = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/api";
    private static boolean isLoggedIn = false;
    private static String loggedInUsername = null;
    private static String loggedInRole = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nNEWS AGGREGATOR MENU:-");

            if (!isLoggedIn) {
                System.out.println("1) Sign-Up");
                System.out.println("2) Login");
                System.out.println("3) Exit");
            } else {
                System.out.println("Welcome, " + loggedInUsername + " (" + loggedInRole + ")");
                System.out.println("1) View News");
                System.out.println("2) Fetch News");
                if ("ADMIN".equalsIgnoreCase(loggedInRole)) {
                    System.out.println("3) View Users");
                    System.out.println("4) Logout");
                } else {
                    System.out.println("3) Logout");
                }
            }

            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();

            if (!isLoggedIn) {
                if ("1".equals(input)) {
                    handleSignUp(scanner);
                } else if ("2".equals(input)) {
                    handleLogin(scanner);
                } else if ("3".equals(input)) {
                    System.out.println("Exiting application...");
                    return;
                } else {
                    System.out.println("Invalid option.");
                }
            } else {
                if ("1".equals(input)) {
                    viewNews();
                } else if ("2".equals(input)) {
                    fetchNews();
                } else if ("3".equals(input) && "ADMIN".equalsIgnoreCase(loggedInRole)) {
                    viewUsers();
                } else if ("3".equals(input) || "4".equals(input)) {
                    handleLogout();
                } else {
                    System.out.println("Invalid option.");
                }
            }
        }
    }

    private static void handleSignUp(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email (@gmail.com only): ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Role (ADMIN/USER): ");
        String role = scanner.nextLine();

        User user = new User(username, email, password, role);
        try {
            String response = rest.postForObject(BASE_URL + "/auth/signup", user, String.class);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Sign-up failed: " + e.getMessage());
        }
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "text/plain");
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<User> entity = new HttpEntity<>(loginUser, headers);
            ResponseEntity<String> response = rest.exchange(BASE_URL + "/auth/login", HttpMethod.POST, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                isLoggedIn = true;
                loggedInUsername = username;

                ResponseEntity<User[]> allUsers = rest.getForEntity(BASE_URL + "/users", User[].class);
                for (User u : allUsers.getBody()) {
                    if (u.getUsername().equals(username)) {
                        loggedInRole = u.getRole();
                        break;
                    }
                }

                System.out.println("Login successful. Welcome, " + username + "!");
            } else {
                System.out.println(response.getBody());
            }

        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void handleLogout() {
        System.out.println("Logged out: " + loggedInUsername);
        isLoggedIn = false;
        loggedInUsername = null;
        loggedInRole = null;
    }

    private static void viewNews() {
        try {
            ResponseEntity<News[]> response = rest.getForEntity(BASE_URL + "/news", News[].class);
            News[] newsArray = response.getBody();

            if (newsArray != null && newsArray.length > 0) {
                for (News n : newsArray) {
                    if (n.getDescription() != null && !n.getDescription().trim().isEmpty()) {
                        System.out.println("News Description: " + n.getDescription());
                    }
                }
            } else {
                System.out.println("No news available.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing news: " + e.getMessage());
        }
    }

    private static void fetchNews() {
        try {
            String result = rest.getForObject(BASE_URL + "/news/fetch", String.class);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error fetching news: " + e.getMessage());
        }
    }

    private static void viewUsers() {
        try {
            ResponseEntity<User[]> response = rest.getForEntity(BASE_URL + "/users", User[].class);
            User[] users = response.getBody();
            if (users != null) {
                for (User u : users) {
                    System.out.println("Username: " + u.getUsername());
                    System.out.println("Email: " + u.getEmail());
                    System.out.println("Role: " + u.getRole());
                    System.out.println("-----");
                }
            } else {
                System.out.println("No users found.");
            }
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }
    }
}
