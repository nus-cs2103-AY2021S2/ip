package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    /**
     * The task that needs to be done, as a String
     */
    protected String todo;
    /**
     * Whether or not the task is complete
     */
    protected boolean done;

    /**
     * Instantiates a task.
     *
     * @param s
     */
    public Task(String s) {
        this.todo = s;
        this.done = false;
    }

    public void setDone() {
        this.done = true;
    }

    public String getTodo() {
        return this.todo;
    }

    /**
     * Returns a String form of the current Task to be saved onto the hard disk.
     *
     * @return the Task as a String to be saved.
     */
    public String saveToData() {
        if (this.done) {
            return ("T | 1 | " + todo);
        } else {
            return ("T | 0 | " + todo);
        }
    }

    /**
     * Returns a nicely formatted String from the given LocalDateTime.
     *
     * @param date Date to be formatted.
     * @return inputted date as a String.
     */
    public String dateFormat(LocalDateTime date) {
        return (date.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")));
    }

    @Override
    public String toString() {
        if (!this.done) {
            return ("[T][ ] " + todo);
        } else {
            return ("[T][X] " + todo);
        }
    }

}
