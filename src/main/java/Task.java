public class Task {
    String title;
    boolean isDone;
    Task(String title) {
        this.title = title;
        isDone = false;
    }

    String getTitle() {
        return title;
    }

    void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return check + title + "\n";
    }
}
