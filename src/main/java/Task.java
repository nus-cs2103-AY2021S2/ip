public class Task {
    private String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return "[" + (this.done ? "\u2713" : "\u2718") + "]" + this.name;
    }

    public void markDone() {
        this.done = true;
    }

}
