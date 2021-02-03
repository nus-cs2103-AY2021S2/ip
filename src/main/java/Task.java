public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String saveFormat() {
        return (isDone ? "1" : "0") +  " | " + description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
