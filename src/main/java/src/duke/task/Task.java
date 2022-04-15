package duke.task;

import java.time.LocalDate;

/**
 * This Task class is responsible for handling and manipulating task
 * information.
 *
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a task the given description
     *
     * @param description name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * changes the status of the task, done or not
     *
     * @param isDone sets the status to this input
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * returns a "[X] " if task is not done, and "[ ] " if task is done
     *
     * @return "[X] " or "[ ] "
     */
    public String getStatus() {
        String isDoneSymbol = isDone ? "[X] " : "[ ] ";
        return isDoneSymbol + this.description;
    }

    /**
     * returns formatted string based on whether task is done
     *
     * @return a formatted string based on done status
     */
    public String currentStatus() {
        if (this.isDone) {
            return " | 1 | " + this.description + "\n";
        } else {
            return " | 0 | " + this.description + "\n";
        }
    }

    /**
     * checks if the description of the task contains specified word
     *
     * @param word word that is to be found the in task
     * @return true if found, false otherwise
     */
    public boolean doesDescriptionContain(String word) {
        return this.description.contains(word);
    }

    /**
     * gets the current is done status
     * @return true is task is done false if not
     */

    public boolean getDone() {
        return this.isDone;
    }

    /**
     * to be implemented by subclasses if they intend to support the snooze command
     */

    public abstract void changeEventTime(LocalDate newTime);
}
