import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner;
    private DecisionService decisionService;

    public ConsoleInterface(DecisionService decisionService) {
        this.scanner = new Scanner(System.in);
        this.decisionService = decisionService;
    }

    public void start() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Decision Log System ---");
            System.out.println("1. Add Decision");
            System.out.println("2. View Decisions");
            System.out.println("3. Search Decisions");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    decisionService.addDecision();
                    break;
                case 2:
                    decisionService.viewDecisions();
                    break;
                case 3:
                    searchDecisions();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchDecisions() {
        int searchChoice = -1;
        do {
            System.out.println("\n--- Search Decisions ---");
            System.out.println("Choose search criteria:");
            System.out.println("1. Search by Component");
            System.out.println("2. Search by Person");
            System.out.println("3. Back to Main Menu");
            System.out.print("Your choice: ");

            try {
                searchChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (searchChoice) {
                case 1:
                    decisionService.searchByComponent();
                    break;
                case 2:
                    decisionService.searchByPerson();
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (searchChoice != 3);
    }
}
