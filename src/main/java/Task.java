public class Task {
    String task;
    Boolean completed;

    Task(String t) {
        this.task = t;
        this.completed = false;
    }

    public void isCompleted() {
        this.completed = true;
    }

    public String completedBox() {
        if (this.completed) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    @Override
    public String toString() {
        return this.completedBox() + this.task;
    }
}
