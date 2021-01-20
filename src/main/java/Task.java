import java.util.HashMap;
import java.util.Map;

public abstract class Task implements Cloneable {

    protected final String name;
    protected final boolean done;

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

    public abstract Task setDone(boolean done);

    @Override
    protected abstract Task clone();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.name);
    }
}
