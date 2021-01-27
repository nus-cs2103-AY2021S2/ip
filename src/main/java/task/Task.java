package task;

public class Task {
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void setDone(boolean b) {
        isDone = b;
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " "+ description);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }
}
