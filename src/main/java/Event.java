import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate timestamp;

    public Event(String n, String t) {
        isDone = false;
        name = n;
        timestamp = LocalDate.parse(t);
    }

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
