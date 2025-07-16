import java.io.Serializable;
import java.time.LocalDate;

public class Decision implements Serializable {
    private LocalDate date;
    private String component;
    private String person;
    private int importanceLevel;
    private String description;

    public Decision(LocalDate date, String component, String person, int importanceLevel, String description) {
        this.date = date;
        this.component = component;
        this.person = person;
        this.importanceLevel = importanceLevel;
        this.description = description;
    }

    public LocalDate getDate() { return date; }
    public String getComponent() { return component; }
    public String getPerson() { return person; }
    public int getImportanceLevel() { return importanceLevel; }
    public String getDescription() { return description; }

    public String toString() {
        return "Decision:" +
                " DATE=" + date +
                ", COMPONENT=" + component +
                ", PERSON=" + person +
                ", IMPORTANCE LEVEL=" + importanceLevel +
                ", DESCRIPTION=" + description;
    }

    public String toFile() {
        return date + "," + component + "," + person + "," + importanceLevel + "," + description;
    }

    public static Decision fromFile(String fileLine) {
        try {
            String[] data = fileLine.split(",", 5);
            LocalDate date = LocalDate.parse(data[0]);
            String component = data[1];
            String person = data[2];
            int importanceLevel = Integer.parseInt(data[3]);
            String description = data[4];
            return new Decision(date, component, person, importanceLevel, description);
        } catch (Exception e) {
            System.out.println("Error parsing decision from file: " + e.getMessage());
            return null;
        }
    }
}
