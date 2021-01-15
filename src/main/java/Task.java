public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void getStatusAndTask() {
        System.out.println("       [" + this.getStatusIcon() + "] " + this.description);
    }

    @Override
    public String toString() {
        return this.description;
    }
}