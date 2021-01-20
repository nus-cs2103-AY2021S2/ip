public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected final String type;

    public Task(String type, boolean isDone, String description) {
        this.type = type;
        this.isDone = false;
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return [X] or [ ] symbols
    }

    public String getTypeIcon() {
        return "[" + type + "]";
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        setDone(true);
    }
}
