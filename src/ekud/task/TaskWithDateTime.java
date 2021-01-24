package ekud.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public abstract class TaskWithDateTime extends Task {
    protected LocalDateTime dateTime;

    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TaskWithDateTime(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public LinkedList<String> export() {
        LinkedList<String> list =  super.export();
        list.addLast(dateTime.toString());
        return list;
    }
}
