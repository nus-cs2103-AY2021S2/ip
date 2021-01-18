public class ListObject {
    String task;
    boolean isDone;

    public ListObject(String task) {
        this.task = task;
        this.isDone = false;
    }

    void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.task : "[ ] " + this.task;
    }
}
