public class Task {
    protected char type;
    protected String description;
    protected boolean isDone;

    public Task(char type, int isDone, String description) {
        this.type = type;
        this.description = description;
        this.isDone = isDone == 1;
    }

    public String getFileString() {
        return String.format("%c // %d // %s", this.type, isDone ? 1 : 0, this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}