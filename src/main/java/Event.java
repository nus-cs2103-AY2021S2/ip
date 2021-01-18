/**
 * Represents an Event task.
 * Has a date.
 */
public class Event extends Task {
    private String date;

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

    private Event (String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
