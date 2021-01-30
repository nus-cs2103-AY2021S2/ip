package yoda.task;

import java.time.LocalDateTime;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
        super.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "created on " + formatDateTime();
    }
}
