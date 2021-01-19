package main.java.subfiles;

/**
 * The Task class represents a single task created by the
 * user via user input to the Duke program. It contains
 * functions which enable the user to mark the task as done.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class Task {
    /** Name of the task */
    protected String name;

    /** Indicates whether the task has been done by the user */
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[ ][" + (isDone ? "X" : " ") + "] " + name;
    }

}
