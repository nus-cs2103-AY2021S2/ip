package ekud.task;

import java.time.*;
import java.util.*;

public class Deadline extends TaskWithDateTime {
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }

    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addFirst("D");
        return list;
    }
}
