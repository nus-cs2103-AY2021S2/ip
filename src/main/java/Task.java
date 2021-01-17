public class Task {
    protected String description;
    protected boolean isDone;
    public Task( boolean isDone, String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[ ]"); //return [tick] or [ ] symbols
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return getStatusIcon() + " " + description;
    }

    public void markAsDone() {
        setDone(true);
    }

}
