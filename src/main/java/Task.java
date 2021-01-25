import org.json.simple.JSONObject;

import java.time.LocalDate;

/**
 * Represent a task in the task list
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Initialize a new undone task
     * @param name Name of the task
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Set the status of the task; e.g. done or not done
     * @param isDone the status to set to the task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public LocalDate getDate() {
        return null;
    }

    /**
     * toString method overriding its predefined one
     * @return a user-friendly String representation of the task
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[%s] %s", doneMark, name);
    }

    public JSONObject toJsonObject() {
        return null;
    }
}