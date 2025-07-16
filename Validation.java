import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validation {
    public static final Scanner scanner = new Scanner(System.in);

    public static LocalDate promptForDate() {
        while (true) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            if (validateDate(dateInput)) {
                return LocalDate.parse(dateInput);
            } else {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    private static boolean validateDate(String dateInput) {
        try {
            LocalDate.parse(dateInput);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String promptForPersonName() {
        while (true) {
            System.out.print("Enter person's name (First Last): ");
            String person = scanner.nextLine().trim();
            if (!person.isEmpty() && validatePersonName(person)) {
                return person;
            } else {
                System.out.println("Invalid name. Please enter a first and last name.");
            }
        }
    }

    private static boolean validatePersonName(String person) {
        String[] parts = person.split(" ");
        return parts.length >= 2;
    }

    public static String promptForComponent() {
        while (true) {
            System.out.print("Enter component: ");
            String component = scanner.nextLine().trim();
            if (!component.isEmpty()) {
                return component;
            } else {
                System.out.println("Component cannot be empty. Please enter a component.");
            }
        }
    }

    public static int promptForImportanceLevel() {
        while (true) {
            System.out.print("Enter importance level (1-5): ");
            String levelInput = scanner.nextLine();
            if (validateImportanceLevel(levelInput)) {
                return Integer.parseInt(levelInput);
            } else {
                System.out.println("Invalid importance level. Please enter a number between 1 and 5.");
            }
        }
    }

    private static boolean validateImportanceLevel(String input) {
        try {
            int level = Integer.parseInt(input);
            return level >= 1 && level <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String promptForDescription() {
        while (true) {
            System.out.print("Enter description (it cannot be empty): ");
            String description = scanner.nextLine().trim();
            if (!description.isEmpty()) {
                return description;
            } else {
                System.out.println("Description cannot be empty.");
            }
        }

    }
}
