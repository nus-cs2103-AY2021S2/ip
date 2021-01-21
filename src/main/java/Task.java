/*
Task is the general case for Todo, Deadline, and Event. In future we might change this to interface or smt,
I dont know... depends on future requirements.
 */
public class Task {
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); //return X symbol or empty string
    }

    public void markDone() {
        this.isDone = !this.isDone;
    }

    String description;
    boolean isDone;

    @Override
    public String toString() {
        String doneStatus = "[" + getStatusIcon() + "]";
        return doneStatus + " " + this.description;
    }
}
