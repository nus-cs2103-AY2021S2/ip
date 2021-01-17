public class Task {
    private String task;
    private boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    private void doTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
