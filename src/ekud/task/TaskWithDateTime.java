package ekud.task;

import java.time.*;
import java.time.format.*;
import java.util.*;

public abstract class TaskWithDateTime extends Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");
    protected LocalDateTime dateTime;

    public TaskWithDateTime(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addLast(dateTime.toString());
        return list;
    }
}
