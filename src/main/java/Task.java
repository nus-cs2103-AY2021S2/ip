public class Task {
    private int id;
    private String name;
    private boolean isDone;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.id + ".[" + this.getStatusIcon() + "] " + this.name;
    }
}
