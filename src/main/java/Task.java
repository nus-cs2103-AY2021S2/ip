public class Task {
    protected String name;
    protected boolean done; 

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[X] %s", name);
        } else {
            return String.format("[ ] %s", name);
        }
    }

    void markAsDone() {
        this.done = true;
    }
}
