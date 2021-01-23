import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents an deadline task.
 * Has a due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Factory method for creating deadline task.
     * @param input Description of the task and its due date. Due date should be indicated after "/by".
     * @return A deadline task
     * @throws DukeException if due date is missing
     */
    public static Deadline createDeadline(String input) throws DukeException {
        String[] details = input.split(" /by ");

        if (!input.contains("/by") || details.length == 1) {
            throw new DukeException("Due date is missing!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format! Please use YYYY-MM-DD");
        }

        return new Deadline(details[0], date);
    }

    private Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                dueDate.format(DateTimeFormatter.ofPattern("d MMM")));
    }
}
