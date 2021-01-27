package tlylt.haha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract representation of a task.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected final String type;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor of a task. Mainly used by child classes to create
     * a detailed task of type Todo, Event or Deadline.
     *
     * @param type        specific task type
     * @param isDone      whether task is set as completed
     * @param description details of task
     */
    protected Task(String type, boolean isDone, String description) {
        this.type = type;
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Constructor of a task. Mainly used by child classes to create
     * a detailed task of type Todo, Event or Deadline.
     * Takes in datetime details for Event and Deadline.
     *
     * @param type        specific task type
     * @param isDone      whether task is set as completed
     * @param description details of task
     */
    protected Task(String type, boolean isDone, String description, LocalDate date, LocalTime time) {
        this(type, isDone, description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns completion status.
     *
     * @return String completion icon.
     */
    protected String getStatusIcon() {
        //return [X] or [ ] symbols
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns type in nicer format.
     *
     * @return String type of task.
     */
    protected String getTypeIcon() {
        return "[" + type + "]";
    }

    /**
     * Returns nicer representation of task for Event and Deadline.
     * Datetime details are parsed and formatted accordingly.
     *
     * @param id Whether this is for Event or Deadline.
     * @return String nicer representation of task details.
     */
    protected String modifiedDescription(String id) {
        String formatDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatTime = time.format(DateTimeFormatter.ofPattern("hh.mm a"));
        String formatDateTime = "(" + id + ": " + formatDate + " " + formatTime + ")";
        return description.substring(0, description.indexOf('/')) + formatDateTime;
    }

    /**
     * Sets task status.
     *
     * @param done Whether done or not done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Getter for isDone
     *
     * @return Boolean task status.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns formatted string for storing into file.
     *
     * @return String formatted task details.
     */
    public String fileStorageFormat() {
        return type + "|" + getIsDone() + "|" + description;
    }

}
