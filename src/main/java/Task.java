public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return 1 if done, 0 if not done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return new String( getStatusIcon() + " | " + description);
    }
}