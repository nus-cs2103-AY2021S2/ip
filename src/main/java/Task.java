public class Task {
    protected String msg;
    protected Boolean isDone = false;

    Task(String msg) {
        this.msg = msg;
        this.isDone = false;
    }

    Task(String msg, Boolean isDone) {
        this.msg = msg;
        this.isDone = isDone;
    }

    public Task setDone() {
        return new Task(this.msg, true);
    }

    @Override
    public String toString() {
        if (isDone.equals(true)) {
            return "[" + "\u2713" + "] " + msg;
        } else {
            return "[ ] " + msg;
        }
    }
}
