public class Task {
    protected final String task;
    protected final boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public Task finishTask() {
        return new Task(this.task, true);
    }

    public String saveString() {
        if (this.isDone) {
            return "1|" + this.task;
        }
        return "0|" + this.task;
    }

    @Override
    public String toString() {
        String tick = " ";
        if (this.isDone){
            tick = "X";
        }
        return "[" + tick + "]" + " " + this.task;
    }
}
