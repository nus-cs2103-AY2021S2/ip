import java.util.List;

/**
 * Represents an Event task.
 * Has a date.
 */
public class Event extends Task {

    private String date;
    private static final String TYPE = "E";

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

        return new Event(details[0], details[1]);
    }

    private Event(String description, String date) {
        super(description);
        this.date = date;
    }

    private Event(boolean isDone, String description, String date) {
        super(description);
        this.date = date;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", TYPE, super.toString(), date);
    }
    
    @Override
    public List<String> exportData() {
        return List.of(TYPE,
                isDone ? "1" : "0",
                description,
                date);
    }

    public static Event importData(String[] args) {
        boolean isDone = args[1].equals("1");
        return new Event(isDone, args[2], args[3]);
    }
}
