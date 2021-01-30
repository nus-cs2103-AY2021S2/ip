package duke.util;

import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    private static final String TYPE = "E";

    /**
     * Factory method for creating Event task.
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

        return new Event(details[0], date);
    }

    private Event(String description, LocalDate date) {
        super(description, date);
    }

    private Event(boolean isDone, String description, LocalDate date) {
        super(description, date);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)",
                TYPE,
                super.toString(),
                date.format(DateTimeFormatter.ofPattern("d MMM")));
    }

    /**
     * Export data into a standardised format.
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
     */
    protected static Event importData(String[] args) {
        boolean isDone = args[1].equals("1");
        return new Event(isDone, args[2], LocalDate.parse(args[3]));
    }
}
