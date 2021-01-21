public class Task {
    protected String name;
    protected boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public void status(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
