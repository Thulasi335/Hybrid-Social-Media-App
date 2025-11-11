package com.socialmedia.ui;

import com.socialmedia.model.User;
import com.socialmedia.service.AuthService;
import com.socialmedia.service.PostService;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthService authService = new AuthService();
    private final PostService postService = new PostService();

    public void start() {
        while (true) {
            System.out.println("\n=== Social Media App ===");
            System.out.println("Register click 1");
            System.out.println("Login click 2");
            System.out.println("Exit click 3");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Exiting... Goodbye! Have a nice day!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void register() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authService.register(username, email, password)) {
            System.out.println("Registered successfully!");
        } else {
            System.out.println("Registration failed!");
        }
    }

    private void login() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Optional<User> userOpt = authService.login(username, password);
        userOpt.ifPresent(this::loggedInMenu);
    }

    private void loggedInMenu(User user) {
        while (true) {
            System.out.println("\nWelcome to MEFJ App " + user.getUsername());
            System.out.println("1 For Create Post");
            System.out.println("2 View Feed");
            System.out.println("3 Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter post content: ");
                    String content = scanner.nextLine();
                    postService.createPost(user.getID(), user.getUsername(), content);
                }
                case 2 -> postService.viewFeed();
                case 3 -> {
                    System.out.println("Bye Logged out!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
