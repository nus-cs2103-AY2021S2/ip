public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + this.name;
    }
}
