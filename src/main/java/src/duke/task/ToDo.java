package duke.task;

import java.time.LocalDate;

/**
 * Todo implementation of the super class task. Includes method to properly
 * format the printing of todo tasks.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    @Override
    public String currentStatus() {
        return "T" + super.currentStatus();
    }

    @Override
    public void changeEventTime(LocalDate newTime) {

    }
}
