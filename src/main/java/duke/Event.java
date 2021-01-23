package duke;

import java.time.LocalDateTime;

public class Event extends Task {
    public LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        isDone = false;
    }
    @Override
    public String getDate() {
        return " (at: "
                + date.getDayOfMonth() + " "
                + date.getMonth().toString().substring(0, 3) + " "
                + date.getYear() + ", " + date.getHour() + ":"
                + date.getMinute() + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getDate() + "\n";
    }
}
