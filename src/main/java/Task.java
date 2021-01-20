public class Task implements Cloneable {
    private final String name;
    private final boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public Task setDone(boolean done) {
        return new Task(this.name, done);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.name);
    }

    @Override
    protected Task clone() {
        return new Task(this.name, this.done);
    }

}
