public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return "[" + (this.isDone ? "\u2713" : "\u2718") + "] " + this.name;
    }

    public void markDone() {
        this.isDone = true;
    }

}
