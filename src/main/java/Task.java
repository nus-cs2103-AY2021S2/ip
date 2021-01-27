import java.time.LocalDate;

/**
 * An abstract class of the types of tasks an user can input.
 */
public abstract class Task {
    /** Describes the Task that is to be done */
    protected String description;
    /** Shows whether the task is completed or not */
    protected boolean isDone = false;

    /**
     * Constructor for a Task object.
     * @param description Describes what the task is.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Abstract method that returns the first letter of the Task object type.
     * @return String with one letter.
     */
    public abstract String getInitial();

    /**
     * Getter that returns the date when the task is to be completed by.
     * @return The variable date;
     */
    public abstract LocalDate getDate();

    /**
     * Setter that sets the value of isDone to indicate that the task has been completed.
     */
    public void finished() {
        isDone = true;
    }

    /**
     * Visual representation for the toString() method that indicates whether a task is done or not.
     * @return String showing [X] if the Task is completed and [ ] otherwise.
     */
    protected String statusIcon() {
        if (isDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    /**
     * Returns whether the Task is completed or not.
     * @return
     */
    public String getDone() {
        if (isDone) {
            return "1";
        }
        return "0";
    }

    /**
     * Returns the description of the Task.
     * @return The variable description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * String representation of a Task object.
     * @return A string with the Task description.
     */
    @Override
    public String toString() {
        return this.statusIcon() + description;
    }
}
