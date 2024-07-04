package org.example;

import com.mongodb.client.MongoDatabase;
import java.util.Scanner;

public class SeaWasteManagementApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoDatabase database = mongoDBConnection.getDatabase();

        UserAuthentication userAuth = new UserAuthentication(database, scanner);
        WasteMonitoring wasteMonitoring = new WasteMonitoring(database, scanner);

        boolean isLoggedIn = false;

        while (true) {
            System.out.println("=== Sea Waste Management System ===");

            if (!isLoggedIn) {
                displayLoginMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        userAuth.registerUser();
                        System.out.println("User registration completed.");
                        break;
                    case 2:
                        isLoggedIn = userAuth.loginUser();
                        if (isLoggedIn) {
                            System.out.println("Login successful. Welcome!");
                        } else {
                            System.out.println("Login failed.");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting Sea Waste Management System.");
                        mongoDBConnection.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } else {
                displayMainMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        wasteMonitoring.monitorWaste();
                        break;
                    case 2:
                        wasteMonitoring.updateWaste();
                        break;
                    case 3:
                        wasteMonitoring.deleteWaste();
                        break;
                    case 4:
                        wasteMonitoring.viewAllWaste();
                        break;
                    case 5:
                        isLoggedIn = false;
                        System.out.println("Logged out successfully.");
                        break;
                    case 6:
                        System.out.println("Exiting Sea Waste Management System.");
                        mongoDBConnection.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }

            // Adding a delay before showing the next input
            try {
                Thread.sleep(2000); // 2-second delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Unexpected interruption.");
            }
        }
    }

    private static void displayLoginMenu() {
        System.out.println("1. Register User");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayMainMenu() {
        System.out.println("1. Monitor Waste");
        System.out.println("2. Update Waste");
        System.out.println("3. Delete Waste");
        System.out.println("4. View All Waste");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
}
