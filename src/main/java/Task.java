public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X or blank space, X means done
    }

    public void done() {
        this.isDone = true;
    }

    public char type() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}