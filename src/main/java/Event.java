import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String desc, String at) {
        super(desc);
        this.at = LocalDate.parse(at);
    }

    public Event(String desc, boolean isDone, String at) {
        super(desc, isDone);
        this.at = LocalDate.parse(at);
    }

    static public Event parse(String input) throws DukeException {
        String[] arr = input.split("event");
        if (arr.length < 2) throw new DukeException("Event description cannot be empty");

        String body = arr[1].strip();
        if(!body.trim().isEmpty()) {
            String[] parts = body.split("/at");
            String desc = parts[0].strip();
            String at = parts[1].strip();
            return new Event(desc, at);
        } else {
            throw new DukeException("Event description cannot be empty");
        }
    }

    @Override
    public String toFileString() {
        return String.format("%s|%b|%s|%s", "E", isDone, desc, at);
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + desc + " (at: " + at + ")";
    }
}
