public class Task {
    String name;
    boolean done;

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void status(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
