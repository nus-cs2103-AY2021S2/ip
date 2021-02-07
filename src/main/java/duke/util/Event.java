package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents an Event task.
 * <br>An event task contains a description and a date when the event is occuring.
 */
public class Event extends Task {

    private static final String TYPE = "E";

    private Event(String description, LocalDate date) {
        super(description, date);
    }

    private Event(String description, LocalDate date, boolean isDone) {
        super(description, date);
        this.isDone = isDone;
    }

    /**
     * Factory method for creating Event task.
     *
     * @param input Description of the task and its date.
     *     Date should be indicated after "/at".
     * @return An Event task.
     * @throws DukeInputException If date is missing or wrong format.
     */
    public static Event createEvent(String input) throws DukeInputException {
        String[] details = input.split(" /at ");

        if (!input.contains("/at") || details.length == 1) {
            throw new DukeInputException("Please include a date for the event!");
        }

        LocalDate date;

        try {
            date = LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new DukeInputException("Wrong date format! Please use YYYY-MM-DD");
        }

        assert date != null : "Date was not parsed.";
        return new Event(details[0], date);
    }

    /**
     * Returns String in the form "[Type] task".
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)",
                TYPE,
                super.toString(),
                date.format(DateTimeFormatter.ofPattern("d MMM")));
    }

    /**
     * Export data into a standardised format.
     *
     * @return List of Event details.
     */
    @Override
    protected List<String> exportData() {
        return List.of(TYPE,
                isDone ? "1" : "0",
                description,
                date.toString());
    }

    /**
     * Import data from standardised format.
     *
     * @param args Event details.
     * @return Event object.
     */
    protected static Event importData(String[] args) {
        assert args[1].equals("1") || args[1].equals("0") : "Parser.checkImportFormat() missed an invalid input";

        boolean isDone = args[1].equals("1");
        return new Event(args[2], LocalDate.parse(args[3]), isDone);
    }

    /**
     * Returns a new Event task marked as done.
     *
     * @return An Event object.
     */
    @Override
    public Event markDone() {
        return new Event(description, date, true);
    }
}
