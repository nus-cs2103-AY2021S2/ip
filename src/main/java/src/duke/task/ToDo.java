package duke.task;

import java.time.LocalDate;

/**
 * Todo implementation of the super class task. Includes method to properly
 * format the printing of todo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a task the given description
     *
     * @param description name of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * returns well formatted event status for the bot to print to the user
     *
     * @return status of event with relevant information
     */
    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    /**
     * returns current status of event
     *
     * @return current status of event
     */
    @Override
    public String currentStatus() {
        return "T" + super.currentStatus();
    }

    /**
     * Does nothing since todo tasks do not track time
     */
    @Override
    public void changeEventTime(LocalDate newTime) {

    }
}
