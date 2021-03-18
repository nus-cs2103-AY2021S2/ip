import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline type of tasks.
 */
public class Deadlines extends Task{
    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadlines(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns String format of deadline
     * @return String format of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public void editTask(String newDescription) {
        super.editTask(newDescription);
    }

    /**
     * Formats data for saving into text file.
     * @return D | isDone | description | by
     */
    @Override
    public String formatData() {
        return "D | " + super.formatData() + " | " + by;
    }
}
