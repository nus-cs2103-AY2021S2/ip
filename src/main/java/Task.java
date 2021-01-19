import java.util.ArrayList;

public class Task {

    private String name;
    private boolean done = false;

    public Task (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public void completed() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.getDone()) {
            return "[X] " + this.getName();
        } else {
            return "[ ] " + this.getName();
        }
    }

}
