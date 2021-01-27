public class Task {
    protected String name;
    protected boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName(Task task) {
        return task.name;
    }

    public boolean getStatus(Task task) {
        return task.done;
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
