package duke.task;

public class Task {

    protected int id;
    protected String description;
    protected boolean isDone;

    public Task(String description, int id) {
        this.description = description;
        this.id = id;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public int getID() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    protected String checkBoxToString() {
        if (isDone) {
            return "[X] ";
        } else {
            return "[] ";
        }
    }

}
