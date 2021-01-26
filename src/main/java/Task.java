public class Task {
    protected final String task;
    private final boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public Task finishTask() {
        System.out.println("Nice! I've marked this task as done: ");
        return new Task(this.task, true);
    }

    @Override
    public String toString() {
        String tick = " ";
        if (this.done){
            tick = "X";
        }
        return "[" + tick + "]" + " " + this.task;
    }
}
