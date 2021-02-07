package lihua.tasks;

import java.time.LocalDate;

import org.json.simple.JSONObject;

/**
 * Represent a task in the task list
 */
public class Task {
    /** The name/description/content of the task */
    protected String name;
    /** Indicator of whether the task is done or not */
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
     * Sets the status of the task; e.g. done or not done
     * @param isDone the status to set to the task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the date for the class.
     *
     * @return NULL as this is a dummy method.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * toString method overriding its predefined one
     * @return a user-friendly String representation of the task
     */
    @Override
    public String toString() {
        String doneMark = isDone ? "X" : " ";
        return String.format("[%s] %s", doneMark, name);
    }

    /**
     * Wraps the task object inside a json object to be stored in hard disk.
     *
     * @return NULL as this is a dummy method.
     */
    public JSONObject toJsonObject() {
        return null;
    }
}
