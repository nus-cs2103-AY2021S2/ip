package task;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[X]"); //return tick or X symbols
    }

    public String getTypeIcon() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String tokenize();

    @Override
    public String toString() {
        return this.description;
    }

}
