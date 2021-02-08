package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Abstract class for tasks. Contains information about the task description and
 * whether is has been carried out.
 */
public abstract class Task {
    private String content;
    private boolean isDone;

    /**
     * Abstract Class constructor.
     *
     * @param content description of the task.
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /**
     * Information needed to be write/read in/to file.
     * In the format of Type|
     *
     * @return String representation in file
     */
    public abstract String toFileString();

    /**
     * String representation of whether the task is done.
     * To be used by the concrete implementation of Tasks only.
     *
     * @return [X] if done [ ] otherwise.
     */
    public String toString() {
        String string = "";
        //Printing isDone
        string += String.format("[%s] ", isDone ? "X" : " ");
        string += this.content;
        return string;
    }

    /**
     * Attempts to parse the given string into a defined date format
     * if the appropriate format is found.
     * @param string string representing the time of task
     * @return properly formatted string.
     */
    public String parseDate(String string) {
        try {
            LocalDate date = LocalDate.parse(string);
            return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (DateTimeParseException e) {
            return string;
        }
    }

    /**
     * Set Task to be done.
     *
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets the task description.
     *
     * @return Description of duke.task.Task
     */
    public String getDesc() {
        return this.content;
    }

    /**
     * Gets the status of whether the task is done or not.
     *
     * @return Boolean representation of whether task is done or not.
     */
    public boolean getDone() {
        return this.isDone;
    }
}
