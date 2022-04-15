package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private Event(String description, LocalDate date, boolean isDone, boolean isHighPriority) {
        super(description, date);
        this.isDone = isDone;
        this.isHighPriority = isHighPriority;
    }

    /**
     * Factory method for creating Event task.
     *
     * @param input Description of the task and its date.
     *     Date should be indicated after "/at".
     * @return An Event task.
     */
    public static Event createEvent(String input) {
        String[] details = input.split(" /at ");
        LocalDate date = LocalDate.parse(details[1]);
        return new Event(details[0], date);
    }

    /**
     * Returns String in the form "[TYPE] task".
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
                isHighPriority ? "1" : "0",
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
        assert args[2].equals("1") || args[2].equals("0") : "Parser.checkImportFormat() missed an invalid input";

        boolean isDone = args[1].equals("1");
        boolean isHighPriority = args[2].equals("1");
        return new Event(args[3], LocalDate.parse(args[4]), isDone, isHighPriority);
    }

    /**
     * Returns a new Event task marked as done.
     *
     * @return An Event object.
     */
    @Override
    public Event markDone() {
        return new Event(description, date, true, isHighPriority);
    }

    /**
     * Returns the Event as high priority;
     *
     * @return High priority Event.
     */
    @Override
    public Event setHighPriority() {
        return new Event(description, date, isDone, true);
    }

    /**
     * Returns the Event as low priority;
     *
     * @return low priority Event.
     */
    @Override
    public Event setLowPriority() {
        return new Event(description, date, isDone, false);
    }
}
