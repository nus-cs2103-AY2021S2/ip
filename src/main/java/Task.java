public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setIsDone() {
        this.isDone = true;
    }

}
