public class Task {
    private final String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return "[" + (this.done ? "\u2713" : "\u2718") + "] " + this.name;
    }

    public void markDone() {
        this.done = true;
    }

}
