package duke.task;

import java.time.LocalDate;

/**
 * Represents a task
 */
public abstract class Task {
    private String tag;
    private boolean isComplete;
    private String detail;
    protected LocalDate date;

    /**
     * Constructor 
     * 
     * @param tag Indentification for the task
     * @param detail Details of the task
     * @param date Date associated with the task
     */
    protected Task(String tag, String detail, LocalDate date) {
        this.tag = tag;
        this.isComplete = false;
        this.detail = detail;
        this.date = date;
    }

    /**
     * Sets the completion of a task
     * 
     * @param isComplete Boolean representing if the task is done. True for done, False for not.
     */
    public void setCompletion(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Gets the identifier tag of the task
     * 
     * @return String representing the identifier tag
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Gets the completion status of the task
     * 
     * @return Boolean representing the completion status of the task. True if complete, false if not.
     */
    public boolean checkIsComplete() {
        return this.isComplete;
    }

    /**
     * Gets the details of the task
     * 
     * @return String of the details of the task
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * Gets the date of the task
     * 
     * @return Date associated with the task
     */
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String status = this.isComplete ? "[X] " : "[ ] ";
        return "[" + this.tag + "]" + status + this.detail;
    }
}
