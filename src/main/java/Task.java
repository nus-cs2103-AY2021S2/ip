public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[X] %s", this.name);
        } else {
            return String.format("[ ] %s", this.name);
        }
    }

    public void setDone() {
        this.done = true;
    }
}
