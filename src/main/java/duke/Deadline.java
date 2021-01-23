package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

    public LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        isDone = false;
    }

    @Override
    public String getDate() {
        return " (by: "
                + date.getDayOfMonth() + " "
                + date.getMonth().toString().substring(0, 3) + " "
                + date.getYear() + ", " + date.getHour() + ":"
                + date.getMinute() + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDate() + "\n";
    }
}
