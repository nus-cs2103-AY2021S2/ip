public class Task {
    String task;
    boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Marks the task as done and prints out to console that task is done.
     */
    private void done() {
        this.isDone = true;
        Printer.printWithStyle(new String[] {"Nice! I've marked this task as done:", this.toString()});
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.task : "[ ] " + this.task;
    }
}
