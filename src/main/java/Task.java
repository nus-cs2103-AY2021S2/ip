public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String doneToString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + this.doneToString();
    }

}
