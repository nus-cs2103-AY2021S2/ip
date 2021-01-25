package ekud.task;

import java.time.*;
import java.util.*;

public class EventTask extends TaskWithDateTime {
    public EventTask(String description, LocalDateTime at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }

    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addFirst("E");
        return list;
    }
}
