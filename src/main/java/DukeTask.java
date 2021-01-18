public class DukeTask {
    protected String name;
    protected boolean isDone;

    public DukeTask(String name) {
        this.name = name;
        this.isDone = false;
    }

    public DukeTask(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public DukeTask markDone() {
        return new DukeTask(this.name, true);
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String toString() {
        return this.name;
    }
}
