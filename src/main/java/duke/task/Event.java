package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime start;
    public LocalDateTime end;

    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc, false);
        this.start = start;
        this.end = end;
    }

    public Event(String desc, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getDesc() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        return this.desc + " (Start: " + this.start.format(formatter) + " | End: " + this.end.format(formatter) + ")";
    }

    @Override
    public String getTypeSymbol() {
        return "E";
    }

    @Override
    public String toSaveInfoString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
        return this.getTypeSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc + " | " +
                this.start.format(formatter) + " | " + this.end.format(formatter);
    }
}
