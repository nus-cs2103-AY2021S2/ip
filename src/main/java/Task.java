public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }
    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.name;
        }
        return "[ ] " + this.name;
    }
}
