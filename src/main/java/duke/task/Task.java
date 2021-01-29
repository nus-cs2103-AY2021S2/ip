package duke.task;

import java.util.Scanner;

/**
 * Task is a parent class of three subclasses: Todo, Event and Deadline.
 * Task has a description of the task and a boolean
 * isDone representing whether the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Changes the field isDone to true.
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Returns a string representation of the task to be stored in the
     * hard disk.
     *
     * @return A String representing the task.
     */
    String toFileString() {
        return "";
    }

    @Override
    public String toString(){
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
