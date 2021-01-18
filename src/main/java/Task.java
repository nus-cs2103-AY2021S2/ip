public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        done = false;
    }

    public void completed() {
        done = true;
    }

    public String toString() {
        String status = done ? "X" : " ";
        return String.format("[%s] %s", status, description);
    }
}
