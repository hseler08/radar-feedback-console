import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "";

        System.out.println("Choose an option:");
        System.out.println("1. Load an existing file");
        System.out.println("2. Create a new file");

        int choice = -1;
        while (choice != 1 && choice != 2) {
            try {
                System.out.print("Your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    System.out.print("Enter the path to the existing file: ");
                    filePath = scanner.nextLine().trim();
                    File file = new File(filePath);
                    if (!file.exists()) {
                        System.out.println("The file does not exist. Please check the path.");
                        choice = -1;
                    }
                } else if (choice == 2) {
                    System.out.print("Enter the path to create a new file: ");
                    boolean fileCreated = false;

                    while (!fileCreated) {
                        filePath = scanner.nextLine().trim();
                        File file = new File(filePath);

                        if (!file.exists()) {
                            try {
                                if (file.createNewFile()) {
                                    System.out.println("New file created: " + filePath);
                                    fileCreated = true;
                                } else {
                                    System.out.println("Failed to create the new file. Try again.");
                                }
                            } catch (IOException e) {
                                System.err.println("Error creating the file: " + e.getMessage());
                                System.out.println("Try again.");
                            }
                        } else {
                            System.out.println("File already exists at the specified path. Try again.");
                        }
                    }

                } else {
                    System.out.println("Invalid choice. Please choose 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        try {
            DecisionService decisionService = new DecisionService(filePath);
            ConsoleInterface consoleInterface = new ConsoleInterface(decisionService);
            consoleInterface.start();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
