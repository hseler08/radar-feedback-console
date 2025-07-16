import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DecisionService {
    private List<Decision> decisions;
    private String filePath;
    public static final Scanner scanner = new Scanner(System.in);

    public DecisionService(String filePath) {
        this.filePath = filePath;
        this.decisions = new ArrayList<>();
        loadDecisions();
    }
    public void addDecision() {
        LocalDate date = Validation.promptForDate();
        String person = Validation.promptForPersonName();
        String component = Validation.promptForComponent();
        int importanceLevel = Validation.promptForImportanceLevel();
        String description = Validation.promptForDescription();

        Decision decision = new Decision(date, component, person, importanceLevel, description);
        decisions.add(decision);
        saveDecisions();
        System.out.println("Decision added.");
    }
    public void viewDecisions() {
        if (decisions.isEmpty()) {
            System.out.println("No decisions recorded.");
        } else {
            for (Decision decision : decisions) {
                System.out.println(decision);
            }
        }
    }

    public void searchByComponent() {
        System.out.print("Enter component to search for: ");
        String componentText = scanner.nextLine().toLowerCase();

        List<Decision> results = searchDecisions(componentText, "component");
        if (results.isEmpty()) {
            System.out.println("No decisions found for the given component.");
        } else {
            System.out.println("Search results for component:");
            for (Decision decision : results) {
                System.out.println(decision);
            }
        }
    }

    public void searchByPerson() {
        System.out.print("Enter person name to search for: ");
        String personText = scanner.nextLine().toLowerCase();

        List<Decision> results = searchDecisions(personText, "person");
        if (results.isEmpty()) {
            System.out.println("No decisions found for the given person.");
        } else {
            System.out.println("Search results for person:");
            for (Decision decision : results) {
                System.out.println(decision);
            }
        }
    }
    public List<Decision> searchDecisions(String text, String criteria) {
        List<Decision> results = new ArrayList<>();
        for (Decision decision : decisions) {
            String person = decision.getPerson().toLowerCase();
            String component = decision.getComponent().toLowerCase();

            if ("person".equals(criteria) && person.contains(text)) {
                results.add(decision);
            } else if ("component".equals(criteria) && component.contains(text)) {
                results.add(decision);
            }
        }
        return results;
    }
    private void saveDecisions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Decision decision : decisions) {
                writer.write(decision.toFile());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving decisions: " + e.getMessage());
        }
    }

    private void loadDecisions() {
        decisions.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Decision decision = Decision.fromFile(line);
                if (decision != null) {
                    decisions.add(decision);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data found, starting fresh.");
        }
    }
}

