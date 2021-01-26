public class Task {
    protected final String task;
    protected final boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public Task finishTask() {
        return new Task(this.task, true);
    }

    public String saveString() {
        if (this.done) {
            return "1|" + this.task;
        }
        return "0|" + this.task;
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
