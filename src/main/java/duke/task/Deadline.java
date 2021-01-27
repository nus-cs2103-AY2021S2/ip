package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime cutOffTime;

    public Deadline(String name, LocalDateTime cutOff) {
        super(name);
        cutOffTime = cutOff;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + cutOffTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm"))+ ")";
    }

    @Override
    public String toFileString() {
        return "D " + super.toFileString() + " | "
                + cutOffTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

