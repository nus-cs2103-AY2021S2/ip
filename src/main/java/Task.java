public abstract class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String toFileString() {
        return String.format("%s|%b|%s", "X", isDone, desc);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + desc;
    }
}
