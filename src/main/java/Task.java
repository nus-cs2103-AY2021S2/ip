public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format((isDone ? "[X] " : "[] ") + this.desc);
    }
}
