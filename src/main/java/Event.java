import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * Has a date.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Factory method for creating Event task.
     * @param input Description of the task and its date. Date should be indicated after "/at".
     * @return An Event task
     * @throws DukeException if date is missing
     */
    public static Event createEvent(String input) throws DukeException {
        String[] details = input.split(" /at ");

        if (!input.contains("/at") || details.length == 1) {
            throw new DukeException("Please include a date for the event!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format! Please use YYYY-MM-DD");
        }

        return new Event(details[0], date);
    }

    private Event (String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                date.format(DateTimeFormatter.ofPattern("d MMM")));
    }
}
