import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String name;
    private LocalDate duration;
    private boolean done;

    Event(String name, String duration) {
        this.name = name;
        this.duration = LocalDate.parse(duration);
        this.done = false;
    }

    Event(String name, String duration, boolean done) {
        this.name = name;
        this.duration = LocalDate.parse(duration);
        this.done = done;
    }

    Event(String name, LocalDate duration, boolean done) {
        this.name = name;
        this.duration = duration;
        this.done = done;
    }

    @Override
    Task done() {
        return new Event(this.name, this.duration.toString(), true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event) {
            Event ev = (Event) obj;
            return ev.name.equals(this.name) && (ev.duration.equals(this.duration)) && (ev.done == this.done);
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[E][X] %s (at: %s)", this.name, this.duration.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
        return String.format("[E][ ] %s (at: %s)", this.name, this.duration.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
