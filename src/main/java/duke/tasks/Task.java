package duke.tasks;

import java.time.LocalDate;

import duke.exception.DukeException;

/**
 * Parent class for all tasks in Duke.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class objects.
     *
     * @param description description for the task created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a cross if the current task is done, otherwise, " ".
     *
     * @return a cross if the task is done, otherwise, " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the isDone status of the current task to be true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the isDone of the task.
     * @return the isDone of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }
    /**
     * Returns the details of the task.
     *
     * @return the details of the task.
     */
    @Override
    public String toString() {
        assert !description.isEmpty() : "task does not have a description!";
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the details of the task for storage.
     *
     * @return the details of the task for storage.
     */
    public String getTaskInfoToStore() {
        String divider = " | ";
        return (isDone ? "1" : "0") + divider
                + description;
    }

    /**
     * Updates the date of a task, if applicable.
     * Currently only supports for deadline.
     *
     * @param newDate newDate new date to be updated.
     * @throws DukeException if the method is called on an non-deadline object.
     */
    public void updateDate(LocalDate newDate) throws DukeException {
        throw new DukeException("Only deadlines can be update currently.");
    }
}
