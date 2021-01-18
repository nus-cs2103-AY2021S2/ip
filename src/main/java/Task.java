public class Task {
    private String msg;
    private Boolean isDone = false;

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
            return "[X] " + msg;
        } else {
            return "[ ] " + msg;
        }
    }
}
