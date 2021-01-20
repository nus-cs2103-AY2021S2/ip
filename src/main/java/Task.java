public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatus() {
        String isDoneSymbol = isDone ? "[X] " : "[ ] ";
        return isDoneSymbol + this.description;
    }
}
