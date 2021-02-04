import java.time.LocalDate;

public class Event extends Task {
    private LocalDate at;

    public Event(String desc, String at) {
        super(desc);
        this.at = LocalDate.parse(at);
    }

    public Event(String desc, boolean isDone, String at) {
        super(desc, isDone);
        this.at = LocalDate.parse(at);
    }

    /**
     * Factory method to create Event task based on input
     *
     * @param input User input
     * @return Event object
     * @throws DukeException If parsing input fails
     */
    public static Event parse(String input) throws DukeException {
        String[] arr = input.split("event");
        if (arr.length < 2) {
            throw new DukeException("Event description cannot be empty");
        }

        String body = arr[1].strip();
        if (!body.trim().isEmpty()) {
            String[] parts = body.split("/at");
            String desc = parts[0].strip();
            String at = parts[1].strip();
            return new Event(desc, at);
        } else {
            throw new DukeException("Event description cannot be empty");
        }
    }

    /**
     * Convert to file string for saving.
     *
     * @return File string
     */
    @Override
    public String toFileString() {
        return String.format("%s|%b|%s|%s", "E", isDone, desc, at);
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + desc + " (at: " + at + ")";
    }
}
