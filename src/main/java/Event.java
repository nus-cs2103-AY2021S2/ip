import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Describes the "event" variation of the Task class. */
public class Event extends Task {
    private LocalDate timestamp;

    /**
     * Returns an Event object that takes in two arguments.
     *
     * @param n The name of the task
     * @param t The string representation of the timestamp
     */
    public Event(String n, String t) {
        isDone = false;
        name = n;
        timestamp = LocalDate.parse(t);
    }

    /**
     * Returns the string representation of the task for storage purposes.
     * @return the string representation
     */
    public String toText() {
        String d = isDone ? "+" : "-";
        return String.format("E | %1$s | %2$s | %3$s", d, name, timestamp);
    }

    @Override
    public String toString() {
        var df = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String t = timestamp.format(df);
        return (isDone ? "[X]" : "[ ]")
            + " Event: "
            + name
            + " by "
            + t;
    }
}
