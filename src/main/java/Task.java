public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.getTask();
    }
}
