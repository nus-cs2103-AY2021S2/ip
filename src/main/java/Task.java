public class Task {

    String title;
    boolean isDone;

    Task(String title) {
        this.title = title;
        isDone = false;
    }

    String getDate() {
        return "";
    }

    public void markDone() {
        isDone = true;
    }
}
