import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class of the types of tasks an user can input.
 */
public abstract class Task {
    /** Describes the Task that is to be done. */
    protected String description;

    /** Shows whether the task is completed or not. */
    protected boolean isDone = false;

    protected boolean isTagged = false;

    /** The date when the Task is expected to occur. */
    protected LocalDate date;

    /** The time when the Task is expected to occur. */
    protected LocalTime time;

    protected String tag = null;

    /**
     * Formats the output date for the String representation of some Task objects.
     */
    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Formats the output date for the String representation of some Task objects.
     */
    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh mm");

    /**
     * Constructor for a Task object.
     * @param description Describes what the task is.
     */
    public Task(String description, LocalDate date, LocalTime time) {
        this.description = description;
        this.date = date;
        this.time = time;
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
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter that returns the date time the task is to be completed by.
     * @return The variable time;
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the description of the Task.
     * @return The variable description.
     */
    public String getDescription() {
        return description;
    }

    public boolean getIsTagged() {
        return isTagged;
    }

    public String getTag() {
        return tag;
    }

    /**
     * Setter that sets the value of isDone to indicate that the task has been completed.
     */
    public void finished() {
        isDone = true;
    }

    /**
     * Adds a tag to the current task.
     * @param description The tag that is to be added.
     */
    public void addTag(String description) {
        isTagged = true;
        tag = description;
    }

    /**
     * Visual representation for the toString() method that indicates whether a task is done or not.
     * @return String showing [X] if the Task is completed and [ ] otherwise.
     */
    protected String statusIcon() {
        if (isDone) {
            return "[X] ";
        }
        return "[ ] ";
    }

    /**
     * Returns whether the Task is completed or not.
     * @return "1" if the Task is completed, and "0" otherwise.
     */
    public String getDone() {
        if (isDone) {
            return "1";
        }
        return "0";
    }

    /**
     * String representation of a Task object.
     * @return A string with the Task description.
     */
    @Override
    public String toString() {
        String outputLine = "[" + this.getInitial() + "]" + this.statusIcon() + description;
        if (this.getIsTagged()) {
            outputLine += " (Tag: " + this.tag + ")";
        }
        return outputLine;
    }

}
