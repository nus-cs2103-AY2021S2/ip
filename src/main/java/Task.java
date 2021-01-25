/**
 * Represents a Task input by users.
 * A Task is represented by a name in the form of a string
 * and a boolean to determine if it has been completed.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }

    public void setIsDone() {
        this.isDone = true;
    }

}
