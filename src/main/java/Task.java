public class Task {
    protected boolean done;
    protected String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public void markDone() {
        this.done = true;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }
}
